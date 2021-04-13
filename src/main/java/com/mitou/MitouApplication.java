package com.mitou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author rice
 */
@SpringBootApplication(scanBasePackages = "com.mitou")
public class MitouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MitouApplication.class, args);
    }

}