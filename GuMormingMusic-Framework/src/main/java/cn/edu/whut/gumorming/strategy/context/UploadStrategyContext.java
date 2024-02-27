package cn.edu.whut.gumorming.strategy.context;

import cn.edu.whut.gumorming.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static cn.edu.whut.gumorming.enums.UploadModeEnum.getStrategy;

/**
 * 上传策略上下文
 */
@Service
public class UploadStrategyContext {
    /**
     * 上传模式
     */
    @Value("${upload.strategy}")
    private String uploadStrategy;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return {@link String} 文件地址
     */
    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(getStrategy(uploadStrategy)).uploadImg(file, path);
    }

}