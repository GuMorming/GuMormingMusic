package cn.edu.whut.gumorming.handler.exception;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.ServiceException;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.model.ResponseResult;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

import static cn.edu.whut.gumorming.enums.HttpCodeEnum.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.handler.exception
 * @createTime : 2024/2/19 14:16
 * @Email : gumorming@163.com
 * @Description : 全局异常处理
 */


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult exceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("异常! {}", e.getMessage());
        // 从异常对象获取提示信息, 封装返回
        return ResponseResult.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MybatisPlusException.class)
    public ResponseResult<?> handleMybatisPlusException(MybatisPlusException e) {
        return ResponseResult.error(PROHIBIT_UPDATE_ALL_TABLE);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseResult<?> handleServiceException(ServiceException e) {
        return ResponseResult.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理Assert异常
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseResult.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseResult.error(ARGUMENT_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * SQL语句错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public ResponseResult<?> handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        return ResponseResult.error(SQL_ERROR.getCode(), "SQL错误:" + e.getMessage());
    }

    /**
     * SpringSecurity
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseResult<?> handleSQLSyntaxErrorException(AccessDeniedException e) {
        return ResponseResult.error(NO_OPERATION_AUTH);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseResult<?> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseResult.error(LOGIN_ERROR);
    }

    @ExceptionHandler(value = InsufficientAuthenticationException.class)
    public ResponseResult<?> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {
        return ResponseResult.error(INSUFFICIENT_AUTHENTICATION);
    }

    @ExceptionHandler(value = SerializationException.class)
    public ResponseResult<?> handleSerializationException(SerializationException e) {
        return ResponseResult.error(SERIALIZE_ERROR);
    }
}