package cn.edu.whut.gumorming.exception;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import lombok.Getter;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.exception
 * @createTime : 2024/2/19 16:44
 * @Email : gumorming@163.com
 * @Description :
 */

@Getter
public final class ServiceException extends RuntimeException {

    /**
     * 返回失败状态码
     */
    private final Integer code = HttpCodeEnum.SYSTEM_ERROR.getCode();

    /**
     * 返回信息
     */
    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }

}