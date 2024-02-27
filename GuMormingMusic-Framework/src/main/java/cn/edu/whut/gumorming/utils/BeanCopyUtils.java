package cn.edu.whut.gumorming.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean拷贝工具类
 */
public class BeanCopyUtils {
    /**
     * Bean拷贝(泛型)
     *
     * @param source
     * @param clazz
     * @param <V>
     * @return
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        V target = null;
        try {
            // 创建目标对象
            target = clazz.newInstance();
//            target = clazz.getDeclaredConstructor().newInstance();
            //实现字段copy
            BeanUtils.copyProperties(source, target);

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        // 返回结果
        return target;
    }

    /**
     * BeanList拷贝(流式编程,泛型)
     *
     * @param sourceList
     * @param clazz
     * @param <S>
     * @param <V>
     * @return
     */
    public static <S, V> List<V> copyBeanList(List<S> sourceList, Class<V> clazz) {
        return sourceList.stream()
                .map(s -> copyBean(s, clazz))
                .collect(Collectors.toList());
    }
}