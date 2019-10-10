package com.ms.gateway.swagger;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public SwaggerProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        routeLocator.getRoutes().subscribe(routes::add);

        routes.stream()
                .map(Route::getUri)
                .map(URI::getAuthority)
                .filter(sn -> sn.endsWith("SERVICE"))
                .distinct()
                .forEach(serviceName -> resources.add(swaggerResource(serviceName, "/" + serviceName + API_URI)));



        // 这是取配置文件中的值,  暂时没用上
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
//        for (RouteDefinition routeDefinition : gatewayProperties.getRoutes()) {
//            if (routes.contains(routeDefinition.getId())) {
//                for (PredicateDefinition predicateDefinition : routeDefinition.getPredicates()) {
//                    if (("Path").equalsIgnoreCase(predicateDefinition.getName())) {
//                        resources.add(swaggerResource(routeDefinition.getId(),
//                                predicateDefinition.getArgs().get("\"pattern\"")
//                                        .replace("/**", API_URI)));
//                    }
//                }
//            }
//        }
//        resources.add(swaggerResource("member", "/MEMBER-SERVICE/v2/api-docs"));
//        resources.add(swaggerResource("order", "/ORDER-SERVICE/v2/api-docs"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
