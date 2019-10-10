package com.ms.core.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/9/5 9:28
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public class SystemContextUtils {

    /**
     * 当前登录的用户名
     *
     * @return java.lang.String
     * @author Zhu Kaixiao
     * @date 2019/9/5 9:37
     **/
    public static String currentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = Optional.ofNullable(authentication).map(Principal::getName).orElse("SYSTEM");
        return username;
    }
}
