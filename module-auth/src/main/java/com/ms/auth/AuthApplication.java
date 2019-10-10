package com.ms.auth;

import com.ms.core.kit.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 17:29
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@EnableAuthorizationServer
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Constant.PACKAGE})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
