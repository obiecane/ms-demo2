package com.ms.core.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ms.core.kit.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * SwaggerConfiguration
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:11
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements EnvironmentAware {

    private String name;

    @Override
    public void setEnvironment(final Environment environment) {
        name = environment.getProperty("spring.application.name");
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder authorizationPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        authorizationPar.name("Authorization").description("Authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(authorizationPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constant.PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(name)
                .description("")
                .version("")
                .build();
    }



    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8009/oauth/token");

        return new OAuthBuilder()
                .name("oauth2-resource")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("oauth2-resource", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted!")
        };
    }





//    @Bean
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
//        return new AlternateTypeRuleConvention() {
//            @Override
//            public int getOrder() {
//                return Ordered.HIGHEST_PRECEDENCE;
//            }
//
//            @Override
//            public List<AlternateTypeRule> rules() {
//                return Lists.newArrayList(newRule(resolver.resolve(IPage.class), resolver.resolve(Page.class)));
//            }
//        };
//    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(String.format("%s.generated.%s", IPage.class.getPackage().getName(), IPage.class.getSimpleName()))
                .withProperties(Lists.newArrayList(property(Integer.class, "pageNo"), property(Integer.class, "pageSize"), property(String.class, "sort")))
                .build();
    }

    private AlternateTypePropertyBuilder property(Class<?> type, String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }

    @Data
    @ApiModel
    private static class Page {
        @ApiModelProperty("页码, 从1开始计数")
        private Integer pageNo;
        @ApiModelProperty("页面大小")
        private Integer pageSize;

    }
}
