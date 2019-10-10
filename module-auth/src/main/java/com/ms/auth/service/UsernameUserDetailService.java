package com.ms.auth.service;

import com.ms.auth.dao.AccountRepository;
import com.ms.auth.entity.Account;
import com.ms.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/30 13:39
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
@Service
@AllArgsConstructor
public class UsernameUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login username: {}", username);
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw  new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = account.getRoles();
        //这个权限牵涉到底层的投票机制，默认是一票制AffirmativeBased：如果有任何一个投票器运行访问，请求将被立刻允许，而不管之前可能有的拒绝决定
        // RoleVoter投票器识别以"ROLE_"为前缀的role,这里配置已ROLE_前缀开头的role
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(Role::getName)
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                .collect(Collectors.toList());

        return new User(username, passwordEncoder.encode(account.getPassword()), authorities);
    }



}
