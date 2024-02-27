package cn.edu.whut.gumorming.model;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

import static cn.edu.whut.gumorming.enums.HttpCodeEnum.SUCCESS;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model
 * @createTime : 2024/2/18 14:06
 * @Email : gumorming@163.com
 * @Description : 响应结果类,统一响应格式
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Schema(title = "响应结果类", description = "统一响应格式")
public class ResponseResult<T> implements Serializable {
    @Schema(title = "返回状态")
    private Boolean flag;

    @Schema(title = "状态码")
    private Integer code;

    @Schema(title = "返回信息")
    private String msg;

    @Schema(title = "返回数据")
    private T data;

    public static <T> ResponseResult<T> ok() {
        return buildResponseResult(true, null, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public static <T> ResponseResult<T> ok(T data) {
        return buildResponseResult(true, data, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public static <T> ResponseResult<T> error(HttpCodeEnum httpCodeEnum) {
        return buildResponseResult(false, null, httpCodeEnum.getCode(), httpCodeEnum.getMsg());
    }


    public static <T> ResponseResult<T> error(Integer code, String message) {
        return buildResponseResult(false, null, code, message);
    }

    private static <T> ResponseResult<T> buildResponseResult(Boolean flag, T data, Integer code, String message) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setFlag(flag);
        r.setData(data);
        r.setCode(code);
        r.setMsg(message);
        return r;
    }
}