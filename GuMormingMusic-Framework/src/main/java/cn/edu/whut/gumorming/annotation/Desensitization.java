package cn.edu.whut.gumorming.annotation;

import cn.edu.whut.gumorming.annotation.serialize.DesensitizationSerialize;
import cn.edu.whut.gumorming.enums.DesensitizationTypeEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.annotation
 * @createTime : 2024/2/19 18:36
 * @Email : gumorming@163.com
 * @Description : 脱敏注解
 */

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {

    /**
     * 脱敏数据类型，在MY_RULE的时候，startInclude和endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.MY_RULE;

    /**
     * 脱敏开始位置（包含）
     */
    int startInclude() default 0;

    /**
     * 脱敏结束位置（不包含）
     */
    int endExclude() default 0;

}