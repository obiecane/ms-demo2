package com.ms.order.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.ms.core.kit.JcResult;
import com.ms.order.feign.MemberFeign;
import com.ms.order.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 15:59
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberFeign memberFeign;


    @Override
    public boolean validMember(long id) {
        JcResult jcResult = memberFeign.validMember(id);
        Object data = jcResult.getData();
        return data instanceof Boolean && (boolean) data;
    }
}
