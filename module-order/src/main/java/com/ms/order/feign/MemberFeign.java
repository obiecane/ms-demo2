package com.ms.order.feign;

import com.ms.core.kit.JcResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 会员服务远程调用
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 9:14
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@FeignClient(value = "member-service", fallback = MemberFeignFallbackImpl.class)
public interface MemberFeign {

    @GetMapping(value = "/members/valid/{id}")
    JcResult validMember(@PathVariable long id);

}
