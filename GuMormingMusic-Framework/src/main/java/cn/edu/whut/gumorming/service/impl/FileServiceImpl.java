package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.File;
import cn.edu.whut.gumorming.exception.ServiceException;
import cn.edu.whut.gumorming.mapper.FileMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.FileQuery;
import cn.edu.whut.gumorming.model.request.FolderRequest;
import cn.edu.whut.gumorming.model.response.FileResponse;
import cn.edu.whut.gumorming.service.FileService;
import cn.edu.whut.gumorming.strategy.context.UploadStrategyContext;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.FileUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static cn.edu.whut.gumorming.constants.CommonConstant.FALSE;
import static cn.edu.whut.gumorming.constants.CommonConstant.TRUE;

/**
 * (File)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 10:50:04
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public PageResult<FileResponse> listFileVOList(FileQuery fileQuery) {
        // 构造查询条件
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<File>()
                .eq(StringUtils.hasText(fileQuery.getFilePath()), File::getFilePath, fileQuery.getFilePath());
        // 分页
        Page<File> page = new Page<>(fileQuery.getCurrent(), fileQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<FileResponse> fileResponses = BeanCopyUtils.copyBeanList(page.getRecords(), FileResponse.class);

        return new PageResult<>(fileResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> uploadFile(MultipartFile file, String filePath) {
        String uploadPath = "/".equals(filePath) ? filePath : filePath + "/";
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, uploadPath);

        saveFile(file, url, filePath);

        return ResponseResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> createFolder(FolderRequest folder) {
        String fileName = folder.getFileName();
        String filePath = folder.getFilePath();
        // 判断目录是否存在
        File file = baseMapper.selectOne(new LambdaQueryWrapper<File>()
                .select(File::getId)
                .eq(File::getFilePath, folder.getFilePath())
                .eq(File::getFileName, fileName));
        Assert.isNull(file, "目录已存在");
        // 创建目录
        java.io.File directory = new java.io.File(localPath + filePath + "/" + fileName);
        if (FileUtils.mkdir(directory)) {
            File newFile = File.builder()
                    .fileName(fileName)
                    .filePath(filePath)
                    .isDir(TRUE)
                    .build();
            baseMapper.insert(newFile);
        } else {
            throw new ServiceException("创建目录失败");
        }
        return ResponseResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> deleteFile(List<Integer> fileIdList) {
        List<File> files = baseMapper.selectList(new LambdaQueryWrapper<File>()
                .select(File::getFileName,
                        File::getFilePath,
                        File::getExtendName,
                        File::getIsDir)
                .in(File::getId, fileIdList));
        // 删除数据库中的文件信息
        baseMapper.deleteBatchIds(fileIdList);
        files.forEach(file -> {
            java.io.File File;
            String fileName = localPath + file.getFilePath() + "/" + file.getFileName();
            // 若为目录
            if (file.getIsDir().equals(TRUE)) {
                // 删除数据库中剩余的子文件
                String filePath = file.getFilePath() + file.getFileName();
                baseMapper.delete(new LambdaQueryWrapper<File>()
                        .eq(cn.edu.whut.gumorming.entity.File::getFilePath, filePath));
                // 删除目录
                File = new java.io.File(fileName);
                if (File.exists()) {
                    FileUtils.deleteFile(File);
                }
            }
            // 若为文件
            else {
                // 删除文件
                File = new java.io.File(fileName + "." + file.getExtendName());
                if (File.exists()) {
                    File.delete();
                }
            }
        });
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> downloadFile(Integer fileId) {
        // 查询文件信息
        File file = baseMapper.selectOne(new LambdaQueryWrapper<File>()
                .select(File::getFilePath, File::getFileName,
                        File::getExtendName, File::getIsDir)
                .eq(File::getId, fileId));
        Assert.notNull(file, "文件不存在");
        String filePath = localPath + file.getFilePath() + "/";
        // 要下载的不是目录
        if (file.getIsDir().equals(FALSE)) {
            String fileName = file.getFileName() + "." + file.getExtendName();
            downloadFile(filePath, fileName);
        } else {
            // 下载的是目录则压缩下载
            String fileName = filePath + file.getFileName();
            java.io.File src = new java.io.File(fileName);
            java.io.File dest = new java.io.File(fileName + ".zip");
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(dest));
                // 压缩文件
                toZip(src, zipOutputStream, src.getName());
                zipOutputStream.close();
                // 下载压缩包
                downloadFile(filePath, file.getFileName() + ".zip");
            } catch (IOException e) {
                log.error("downloadFile fail:" + e.getMessage());
            } finally {
                if (dest.exists()) {
                    dest.delete();
                }
            }
        }
        return ResponseResult.ok();
    }

    @Override
    public void saveFile(MultipartFile file, String url, String filePath) {
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            File existFile = baseMapper.selectOne(new LambdaQueryWrapper<File>()
                    .select(File::getId)
                    .eq(File::getFileName, md5)
                    .eq(File::getFilePath, filePath));
            if (Objects.nonNull(existFile)) {
                return;
            }
            // 保存文件信息
            File newFile = File.builder()
                    .fileUrl(url)
                    .fileName(md5)
                    .filePath(filePath)
                    .extendName(extName)
                    .fileSize((int) file.getSize())
                    .isDir(FALSE)
                    .build();
            baseMapper.insert(newFile);
        } catch (IOException e) {
            log.error("saveFile is error:" + e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    private void downloadFile(String filePath, String fileName) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            fileInputStream = new FileInputStream(filePath + fileName);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(fileInputStream, outputStream);
        } catch (IOException e) {
            throw new ServiceException("文件下载失败");
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 压缩文件夹
     *
     * @param src             源文件
     * @param zipOutputStream 压缩输出流
     * @param name            文件名
     * @throws IOException IO异常
     */
    private static void toZip(java.io.File src, ZipOutputStream zipOutputStream, String name) throws IOException {
        for (java.io.File file : Objects.requireNonNull(src.listFiles())) {
            if (file.isFile()) {
                // 判断文件，变成ZipEntry对象，放入到压缩包中
                ZipEntry zipEntry = new ZipEntry(name + "/" + file.getName());
                // 读取文件中的数据，写到压缩包
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file);
                int b;
                while ((b = fileInputStream.read()) != -1) {
                    zipOutputStream.write(b);
                }
                fileInputStream.close();
                zipOutputStream.closeEntry();
            } else {
                toZip(file, zipOutputStream, name + "/" + file.getName());
            }
        }
    }
}