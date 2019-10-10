package com.ms.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.ms.core.kit.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDiscoveryClient
@EnableFeignClients
@EnableDistributedTransaction
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {Constant.PACKAGE})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
