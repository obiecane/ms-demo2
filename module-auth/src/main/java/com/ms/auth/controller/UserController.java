package com.ms.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/30 11:49
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "/test")
    public Object test(){
        return "test";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasAnyRole('ROLE_USER')")  //注解验证时默认加上前缀ROLE_，原因后面文章再讲
    public Object user(){
        return "user";
    }

    @GetMapping(value = "/user/a")
    public Object user2(){
        return "user";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public Object admin(){
        return "admin";
    }

    @GetMapping(value = "/admin/b")
    public Object admin2(){
        return "admin";
    }
}