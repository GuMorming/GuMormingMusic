package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.response.BackInfoResponse;
import cn.edu.whut.gumorming.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/21 13:51
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "主页模块")
@RestController
public class HomeController {
    @Autowired
    private InfoService infoService;

    /**
     * 查看后台信息
     *
     * @return {@link ResponseResult<  BackInfoResponse  >} 后台信息
     */
    @Operation(summary = "查看后台信息")
    @GetMapping("/admin")
    public ResponseResult<BackInfoResponse> getBlogBackInfo() {
        return ResponseResult.ok(infoService.getBackInfo());
    }
}