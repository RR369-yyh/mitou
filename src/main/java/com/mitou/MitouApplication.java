package com.mitou;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动类
 *
 * @author rice
 * @since 2021-03-24
 */
@Slf4j
@MapperScan("com.mitou.*.mapper")
@SpringBootApplication(scanBasePackages = "com.mitou")
public class MitouApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext run = SpringApplication.run(MitouApplication.class, args);
        ConfigurableEnvironment env = run.getEnvironment();
        log.info("应用'{}'运行成功！\nSwagger文档访问链接：\thttp://{}:{}/doc.html",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

}
