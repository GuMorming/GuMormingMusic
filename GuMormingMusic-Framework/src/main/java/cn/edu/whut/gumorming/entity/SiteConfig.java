package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (SiteConfig)表实体类
 *
 * @author GuMorming
 * @since 2024-02-21 10:36:56
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("site_config")
public class SiteConfig {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 游客头像
     */
    private String touristAvatar;
    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 网站地址
     */
    private String siteAddress;
    /**
     * 网站简介
     */
    private String siteIntro;
    /**
     * 网站公告
     */
    private String siteNotice;
    /**
     * 建站日期
     */
    private String createSiteTime;
    /**
     * 备案号
     */
    private String recordNumber;
    /**
     * 作者头像
     */
    private String authorAvatar;
    /**
     * 网站作者
     */
    private String siteAuthor;
    /**
     * 关于我
     */
    private String aboutMe;
    /**
     * Github
     */
    private String github;
    /**
     * Gitee
     */
    private String gitee;
    /**
     * 哔哩哔哩
     */
    private String bilibili;
    /**
     * QQ
     */
    private String qq;
    /**
     * 是否评论审核 (0否 1是)
     */
    private Integer commentCheck;
    /**
     * 是否留言审核 (0否 1是)
     */
    private Integer messageCheck;
    /**
     * 是否开启打赏 (0否 1是)
     */
    private Integer isReward;
    /**
     * 微信二维码
     */
    private String weiXinCode;
    /**
     * 支付宝二维码
     */
    private String aliCode;
    /**
     * 是否邮箱通知 (0否 1是)
     */
    private Integer emailNotice;
    /**
     * 社交列表
     */
    private String socialList;
    /**
     * 登录方式
     */
    private String loginList;
    /**
     * 是否开启音乐播放器 (0否 1是)
     */
    private Integer isMusic;
    /**
     * 网易云歌单id
     */
    private String musicId;
    /**
     * 是否开启聊天室 (0否 1是)
     */
    private Integer isChat;
    /**
     * websocket链接
     */
    private String websocketUrl;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;


}