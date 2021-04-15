package com.mitou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author rice
 * @since 2021-03-24
 */
@MapperScan("com.mitou.*.mapper")
@SpringBootApplication(scanBasePackages = "com.mitou")
public class MitouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MitouApplication.class, args);
    }

}
