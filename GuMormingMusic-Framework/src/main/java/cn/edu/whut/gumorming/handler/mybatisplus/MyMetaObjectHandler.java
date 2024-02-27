package cn.edu.whut.gumorming.handler.mybatisplus;

import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*MP自动填充*/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private Integer userId;


    @Override
    public void insertFill(MetaObject metaObject) {
        if (userId == null) {
            userId = SecurityUtils.getUserId();
        }
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);

        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Integer userId = SecurityUtils.getUserId();
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}