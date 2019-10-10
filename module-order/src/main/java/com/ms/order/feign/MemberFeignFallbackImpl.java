package com.ms.order.feign;

import com.ms.core.kit.JcResult;
import org.springframework.stereotype.Component;

/**
 * 会员服务远程调用降级处理
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 16:45
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Component
public class MemberFeignFallbackImpl implements MemberFeign {

    @Override
    public JcResult validMember(long id) {
        return JcResult.builder().code(400).data(false).build();
    }
}
