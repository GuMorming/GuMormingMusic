package cn.edu.whut.gumorming.utils.redis;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.utils
 * @createTime : 2024/2/18 14:00
 * @Email : gumorming@163.com
 * @Description : Redis 使用FastJson2序列化
 */
@Slf4j
public class FastJson2RedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Class<T> clazz;

    static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(
            // 按需加上需要支持自动类型的类名前缀，范围越小越安全
            "cn.***.***"
    );

    public FastJson2RedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        try {
            return JSON
                    .toJSONString(t, JSONWriter.Feature.WriteClassName)
                    .getBytes(DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error("Fastjson2 序列化错误：{}", e.getMessage());
            throw new SerializationException("无法序列化: " + e.getMessage(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        }
        try {
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, clazz, JSONReader.Feature.SupportAutoType);
        } catch (Exception e) {
            log.error("Fastjson2 反序列化错误：{}", e.getMessage());
            throw new SerializationException("无法反序列化: " + e.getMessage(), e);
        }
    }


//    protected JavaType getJavaType(Class<?> clazz) {
//        return TypeFactory.defaultInstance().constructType(clazz);
//    }

}