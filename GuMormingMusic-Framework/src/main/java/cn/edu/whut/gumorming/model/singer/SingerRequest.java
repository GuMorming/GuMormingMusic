package cn.edu.whut.gumorming.model.singer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(title = "歌手Request")
public class SingerRequest {

    @Schema(title = "歌手id")
    private Integer id;

    @NotBlank(message = "歌手名不能为空")
    @Schema(title = "歌手名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(title = "歌手性别(0女 1男 2保密)")
    private Integer sex;

    @Schema(title = "歌手图片")
    private String pic;

    @Schema(title = "歌手出生日期")
    private LocalDateTime birth;

    @Schema(title = "歌手地区")
    private String location;

    @Schema(title = "歌手简介")
    private String introduction;
}