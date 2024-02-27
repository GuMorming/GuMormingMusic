package cn.edu.whut.gumorming.annotation;

import java.lang.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.annotation
 * @createTime : 2024/2/19 9:44
 * @Email : gumorming@163.com
 * @Description : 操作日志注解
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpsLogger {

    /**
     * @return 操作描述
     */
    String value() default "";

}