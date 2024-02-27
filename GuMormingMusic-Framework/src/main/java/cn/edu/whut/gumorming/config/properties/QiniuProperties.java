package cn.edu.whut.gumorming.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.config.properties
 * @createTime : 2024/2/23 10:06
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.qiniu")
public class QiniuProperties {

    /**
     * url 或者 域名
     */
    private String cdnPath;

    /**
     * 存储桶名字
     */
    private String bucketName;

    /**
     * //区域 如huanan hubei
     */
    private String region;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;
}