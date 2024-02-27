package cn.edu.whut.gumorming.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 头像路径
     */
    AVATAR("avatar/", "/avatar", "头像路径"),


    /**
     * 配置图片路径
     */
    CONFIG("config/", "/config", "配置图片路径"),

    /**
     * 轮播图路径
     */
    CAROUSEL("carousel/", "/carousel", "轮播图路径"),

    /**
     * 歌手图片路径
     */
    singerPic("singerPic/", "/singerPic", "歌手图片路径"),
    /**
     * 歌单图片路径
     */
    songListPic("songListPic/", "/songListPic", "歌单图片路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 文件路径
     */
    private final String filePath;

    /**
     * 描述
     */
    private final String description;
}