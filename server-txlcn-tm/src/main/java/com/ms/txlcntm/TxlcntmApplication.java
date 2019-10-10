package com.ms.txlcntm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分布式事务管理服务
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 15:28
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@EnableTransactionManagerServer
@SpringBootApplication
public class TxlcntmApplication {

    public static void main( String[] args ) {
        SpringApplication.run(TxlcntmApplication.class, args);
    }
}
