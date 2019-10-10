package com.ms.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class AccessFilter implements GlobalFilter ,Ordered{

    /** url匹配器 */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Resource
    private RedisTemplate redisTemplate ;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int getOrder() {
        return -500;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//               ServerHttpRequest request = exchange.getRequest();
//        log.info("send {} request to {}", request.getMethod(),request.getPath().toString());
//        if (!pathMatcher.match("/**/v2/api-docs/**", exchange.getRequest().getPath().value())
//                && !pathMatcher.match("/api-auth/**", exchange.getRequest().getPath().value())) {
//
//            String accessToken = extractToken(exchange.getRequest());
//
//            Object params = null;
//            try {
//                if (accessToken != null) {
//                    params = stringRedisTemplate.opsForValue().get("access:" + accessToken);
//                }
//            } catch (Exception e) {
//                log.error("通过token获取信息失败", e);
//            }
//
//            if (params == null) {
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
//                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                JcResult res = JcResult.builder().code(401).message("401 unauthorized").build();
//                byte[] responseByte = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
//                DataBuffer buffer = response.bufferFactory().wrap(responseByte);
//                return response.writeWith(Flux.just(buffer));
//            }
//        }

        return chain.filter(exchange);
    }

    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        try {
            if (CollectionUtils.isNotEmpty(strings)) {
                authToken = strings.get(0).substring("Bearer".length()).trim();
            }

            if (StringUtils.isBlank(authToken)) {
                strings = request.getQueryParams().get("access_token");
                if (strings != null) {
                    authToken = strings.get(0);
                }
            }
        } catch (Exception e) {
            log.debug("从请求头的取token失败", e);
        }

        return authToken;
    }

}