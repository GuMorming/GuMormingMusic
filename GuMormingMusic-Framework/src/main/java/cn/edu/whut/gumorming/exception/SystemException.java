package cn.edu.whut.gumorming.exception;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import lombok.Getter;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming
 * @createTime : 2024/2/19 14:14
 * @Email : gumorming@163.com
 * @Description :
 */
@Getter
public class SystemException extends RuntimeException {
    private int code;

    private String msg;

    public SystemException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}