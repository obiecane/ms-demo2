package com.ms.member.service;

import com.ms.core.kit.BaseService;
import com.ms.member.entity.Member;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:32
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public interface MemberService extends BaseService<Member> {


    boolean validMember(long id);
}
