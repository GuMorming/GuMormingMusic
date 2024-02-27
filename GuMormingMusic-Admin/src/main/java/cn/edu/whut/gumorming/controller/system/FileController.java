package cn.edu.whut.gumorming.controller.system;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.FileQuery;
import cn.edu.whut.gumorming.model.request.FolderRequest;
import cn.edu.whut.gumorming.model.response.FileResponse;
import cn.edu.whut.gumorming.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;

@Tag(name = "文件模块")
@RestController
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 查看文件列表
     *
     * @param fileQuery 查询条件
     * @return {@link ResponseResult< FileResponse >} 文件列表
     */
    @Operation(summary = "查看文件列表")
    @PreAuthorize("hasAuthority('system:file:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<FileResponse>> listFileVOList(FileQuery fileQuery) {
        return ResponseResult.ok(fileService.listFileVOList(fileQuery));
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPLOAD)
    @Operation(summary = "上传文件")
    @Parameters(@Parameter(name = "file", description = "配置图片", required = true, schema = @Schema(type = "MultipartFile")))
    @PreAuthorize("hasAuthority('system:file:upload')")
    @PostMapping("/upload")
    public ResponseResult<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        return fileService.uploadFile(file, path);
    }

    /**
     * 创建目录
     *
     * @param folder 目录信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(ADD)
    @Operation(summary = "创建目录")
    @PreAuthorize("hasAuthority('system:file:createFolder')")
    @PostMapping("/createFolder")
    public ResponseResult<?> createFolder(@Validated @RequestBody FolderRequest folder) {
        return fileService.createFolder(folder);

    }

    /**
     * 删除文件
     *
     * @param fileIdList 文件id列表
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(DELETE)
    @Operation(summary = "删除文件")
    @PreAuthorize("hasAuthority('system:file:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteFile(@RequestBody List<Integer> fileIdList) {
        return fileService.deleteFile(fileIdList);
    }

    /**
     * 下载文件
     *
     * @param fileId 文件id
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "下载文件")
    @GetMapping("/download/{fileId}")
    public ResponseResult<?> downloadFile(@PathVariable("fileId") Integer fileId) {
        return fileService.downloadFile(fileId);
    }

}