package com.ms.member.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.ms.core.kit.BaseServiceImpl;
import com.ms.member.entity.Member;
import com.ms.member.mapper.MemberMapper;
import com.ms.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:32
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member>
        implements MemberService {


    @Override
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public boolean validMember(long id) {
        Member member = getById(id);
        if (member != null) {
            member.setPassword("Pass" + System.currentTimeMillis());
            this.updateById(member);
            return true;
        }
        return false;
    }
}
