package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.File;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.FileQuery;
import cn.edu.whut.gumorming.model.request.FolderRequest;
import cn.edu.whut.gumorming.model.response.FileResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (File)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 10:50:04
 */
public interface FileService extends IService<File> {

    PageResult<FileResponse> listFileVOList(FileQuery fileQuery);

    ResponseResult<?> uploadFile(MultipartFile file, String path);

    ResponseResult<?> createFolder(FolderRequest folder);

    ResponseResult<?> deleteFile(List<Integer> fileIdList);

    ResponseResult<?> downloadFile(Integer fileId);

    public void saveFile(MultipartFile file, String url, String filePath);
}