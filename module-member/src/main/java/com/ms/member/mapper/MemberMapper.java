package com.ms.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.member.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/10/10 9:13
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public interface MemberMapper extends BaseMapper<Member> {


    Member mySelect();


    /**
     * 动态查询
     * @param params
     * @return java.util.List<com.ms.member.entity.Member>
     * @author Zhu Kaixiao
     * @date 2019/10/10 11:55
     **/
    List<Member> mySelect2(@Param("params") Map<String, Object> params);

    Member associateSelect(@Param("id") Long id);
}
