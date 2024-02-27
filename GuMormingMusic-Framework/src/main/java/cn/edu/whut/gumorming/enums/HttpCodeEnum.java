package cn.edu.whut.gumorming.enums;

import lombok.Getter;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.enums
 * @createTime : 2024/2/18 14:10
 * @Email : gumorming@163.com
 * @Description : 返回码及响应信息
 */
@Getter
public enum HttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    /**
     * 客户端
     */
    ARGUMENT_ERROR(400, "参数错误"),
    // 登录
    LOGIN_ERROR(401, "用户名或密码错误"),
    INSUFFICIENT_AUTHENTICATION(402, "认证不足"),
    NO_OPERATION_AUTH(403, "无权限操作"),
    NOT_FOUND(404, "NOT FOUND"),
    /**
     * 服务器端
     */
    SYSTEM_ERROR(500, "操作失败"),

    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_NUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    SERIALIZE_ERROR(505, "序列化错误"),

    COMMENT_CONTENT_NOT_NULL(506, "评论内容不能为空"),
    UPDATE_TYPE_ERROR(507, "上传图片类型须为png或jpg"),
    REGISTER_USERNAME_NULL(508, "用户名不能为空"),
    REGISTER_PASSWORD_NULL(509, "密码不能为空"),
    REGISTER_NICKNAME_NULL(510, "昵称不能为空"),

    REGISTER_EMAIL_NULL(511, "邮箱不能为空"),
    TAG_NAME_NOT_NULL(512, "标签名不能为空"),
    MENU_PARENT_SELF(513, "修改菜单失败,上级菜单不能选择自己"),
    MENU_CHILDREN_EXIST(514, "子菜单存在,不允许删除"),
    DELETE_ADMIN_DENY(515, "不允许删除管理员"),

    REGISTER_EMAIL_VALID(516, "邮箱格式不正确"),
    EMAIL_CODE_INVALID(517, "验证码无效"),
    SQL_ERROR(518, "SQL错误"),
    PROHIBIT_UPDATE_ALL_TABLE(519, "防止全表更新"),
    CATEGORY_NAME_NULL(521, "分类名称为空"),

    USER_IS_DISABLE(522, "您的账户已被禁用"),
    FILE_EXIST(523, "文件已存在");

    final int code;
    final String msg;

    HttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

}