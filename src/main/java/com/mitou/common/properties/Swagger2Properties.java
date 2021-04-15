package com.mitou.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(Swagger2Properties.class)
@ConfigurationProperties(prefix = "mitou.swagger")
@Component
public class Swagger2Properties {


    private String scanBasePackage = "com.mitou";

    private String serverTitle = "mitou快速搭建平台";

    public String getScanBasePackage() {
        return scanBasePackage;
    }

    public void setScanBasePackage(String scanBasePackage) {
        this.scanBasePackage = scanBasePackage;
    }

    public String getServerTitle() {
        return serverTitle;
    }

    public void setServerTitle(String serverTitle) {
        this.serverTitle = serverTitle;
    }
}
