package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.enums.FilePathEnum;
import cn.edu.whut.gumorming.mapper.SiteConfigMapper;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.service.FileService;
import cn.edu.whut.gumorming.service.SiteConfigService;
import cn.edu.whut.gumorming.strategy.context.UploadStrategyContext;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * (SiteConfig)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 10:36:56
 */
@Service
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements SiteConfigService {
    @Autowired
    private SiteConfigMapper siteConfigMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private FileService fileService;

    @Override
    public SiteConfig getSiteConfig() {
        SiteConfig siteConfig = redisCache.getCacheObject(RedisConstants.SITE_SETTING);
        if (Objects.isNull(siteConfig)) {
            // 从数据库中加载
            siteConfig = siteConfigMapper.selectById(1);
            redisCache.setCacheObject(RedisConstants.SITE_SETTING, siteConfig);
        }
        return siteConfig;
    }

    @Override
    public ResponseResult<?> updateSiteConfig(SiteConfig siteConfig) {
        baseMapper.updateById(siteConfig);
        redisCache.deleteObject(RedisConstants.SITE_SETTING);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<String> uploadSiteImg(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath());
        fileService.saveFile(file, url, FilePathEnum.CONFIG.getFilePath());

        return ResponseResult.ok(url);
    }
}