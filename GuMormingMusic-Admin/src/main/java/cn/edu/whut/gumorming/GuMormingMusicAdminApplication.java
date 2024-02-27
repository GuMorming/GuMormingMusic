package cn.edu.whut.gumorming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming
 * @createTime : 2024/2/17 18:05
 * @Email : gumorming@163.com
 * @Description :
 */
@SpringBootApplication
@MapperScan("cn.edu.whut.gumorming.mapper")
@PropertySource("classpath:application-qiniu.properties")
public class GuMormingMusicAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuMormingMusicAdminApplication.class, args);
    }
}