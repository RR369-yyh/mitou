package com.mitou.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * swagger相关配置
 * <p/>
 *
 * @author rice
 * @since 2021-03-24
 */
@EnableConfigurationProperties(Swagger2Properties.class)
@ConfigurationProperties(prefix = "mitou.swagger")
@Component
public class Swagger2Properties {


    private String scanBasePackage = "com.mitou";

    private String serverTitle = "mitou快速搭建平台";

    /**
     * 每次修改代码都可在配置文件重新定义版本，这对于对接和测试来说都很有意义
     */
    private String version = "1.0";

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
