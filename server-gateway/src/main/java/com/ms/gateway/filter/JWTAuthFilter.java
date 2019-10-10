package com.ms.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.ms.core.kit.JcResult;
import com.ms.core.utils.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/30 9:10
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Component
public class JWTAuthFilter implements GatewayFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().getPath();

        //忽略以下url请求
        if(url.contains("/auth-service/")){
            return chain.filter(exchange);
        }

        //从请求头中取得token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(StringUtils.isEmpty(token)){
            return validAuth(exchange, 401, "401 unauthorized");
        }

        //请求中的token是否在redis中存在
        boolean verifyResult = JWTUtil.verify(token);
        if(!verifyResult){
            return validAuth(exchange, 1004, "invalid token");
        }

        return chain.filter(exchange);
    }


    private Mono<Void> validAuth(ServerWebExchange exchange, int code, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.getHeaders().add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        JcResult res = JcResult.builder().code(code).message(msg).build();
        byte[] responseByte = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(responseByte);
        return response.writeWith(Flux.just(buffer));
    }



    @Override
    public int getOrder() {
        return -100;
    }
}
