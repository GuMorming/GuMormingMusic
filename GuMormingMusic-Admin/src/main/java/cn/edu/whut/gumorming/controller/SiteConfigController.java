package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.service.SiteConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.UPDATE;
import static cn.edu.whut.gumorming.constants.OpsTypeConstants.UPLOAD;


@Tag(name = "网站配置模块")
@RestController
@RequestMapping("/admin/site")
public class SiteConfigController {

    @Autowired
    private SiteConfigService siteConfigService;

    /**
     * 获取网站配置
     *
     * @return {@link ResponseResult<SiteConfig>} 网站配置
     */
    @Operation(summary = "获取网站配置")
    @PreAuthorize("hasAuthority('web:site:list')")
    @GetMapping("/list")
    public ResponseResult<SiteConfig> getSiteConfig() {
        return ResponseResult.ok(siteConfigService.getSiteConfig());
    }

    /**
     * 更新网站配置
     *
     * @param siteConfig 网站配置
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPDATE)
    @Operation(summary = "更新网站配置")
    @PreAuthorize("hasAuthority('web:site:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateSiteConfig(@RequestBody SiteConfig siteConfig) {
        return siteConfigService.updateSiteConfig(siteConfig);

    }

    /**
     * 上传网站配置图片
     *
     * @param file 图片
     * @return {@link ResponseResult<String>} 图片路径
     */
    @OpsLogger(UPLOAD)
    @Operation(summary = "上传网站配置图片")
    @Parameters(@Parameter(name = "file", description = "配置图片", required = true, schema = @Schema(type = "MultipartFile")))
    @PreAuthorize("hasAuthority('web:site:upload')")
    @PostMapping("/upload")
    public ResponseResult<String> uploadSiteImg(@RequestParam("file") MultipartFile file) {
        return siteConfigService.uploadSiteImg(file);
    }

}