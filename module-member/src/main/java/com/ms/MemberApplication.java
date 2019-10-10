package com.ms;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.ms.core.kit.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 会员服务
 * @author Zhu Kaixiao
 */
//@EnableDiscoveryClient
//@EnableDistributedTransaction
//@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {Constant.PACKAGE})
public class MemberApplication {
    public static void main( String[] args ) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
