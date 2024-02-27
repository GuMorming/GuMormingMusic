package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.model.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * (SiteConfig)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 10:36:56
 */
public interface SiteConfigService extends IService<SiteConfig> {

    SiteConfig getSiteConfig();

    ResponseResult<?> updateSiteConfig(SiteConfig siteConfig);

    ResponseResult<String> uploadSiteImg(MultipartFile file);
}