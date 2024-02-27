package cn.edu.whut.gumorming.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2023/7/8 13:30
 * @Email : gumorming@163.com
 * @Description :
 */

public interface UploadService {
    String uploadImg(MultipartFile img) throws IOException;
}