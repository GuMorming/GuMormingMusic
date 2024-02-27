package cn.edu.whut.gumorming.strategy;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.ServiceException;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.utils.FileUtils;
import cn.edu.whut.gumorming.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽象上传模板
 */
@Slf4j
@Service
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    @Override
    public String uploadImg(MultipartFile img, String path) {
        try {
            // 根据md5生成文件名
            String fileName = generateFileName(img);
            //  判断类型
            if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg")) {
                throw new SystemException(HttpCodeEnum.UPDATE_TYPE_ERROR);
            }
            // todo 判断文件是否已存在
//            if (!exists(path + fileName)) {
//                throw new SystemException(HttpCodeEnum.FILE_EXIST);
//            }
            // 若不存在,判断通过, 则上传文件
            // 生成日期目录
            String filePath = PathUtils.generateFilePath(fileName);
            // 不存在则继续上传
            upload(path, filePath, img.getInputStream());
            // 返回文件访问路径
            return "http://" + getFileAccessUrl(path + filePath);
        } catch (Exception e) {
            log.info("uploadFile fail, error is {}", e.getMessage());
            throw new ServiceException("文件上传失败");
        }
    }

    private static String generateFileName(MultipartFile img) throws IOException {
        // 获取文件md5值
        String md5 = FileUtils.getMd5(img.getInputStream());
        // 获取文件扩展名
        String extName = FileUtils.getExtension(img);
        // 重新生成文件名
        String fileName = md5 + "." + extName;
        return fileName;
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     *
     * @param path        路径
     * @param fileName    文件名
     * @param inputStream 输入流
     * @throws IOException io异常
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     *
     * @param filePath 文件路径
     * @return {@link String} 文件url
     */
    public abstract String getFileAccessUrl(String filePath);
}