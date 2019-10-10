package com.ms.member.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.ms.member.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberMapperTest {

    @Resource
    MemberMapper mapper;

    @Test
    public void aInsert() {
        Member member = new Member();
        member.setUsername("小嗨");
        member.setPassword("jqsrdayurhvggeigui");
        assertThat(mapper.insert(member)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(member.getId()).isNotNull();
    }


    @Test
    public void mySelect() {
        Member member = mapper.mySelect();
        System.out.println(member);
    }

    @Test
    public void MySelect2() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("username", "小");
//        param.put("password", "小");
//        param.put("salt", "小");
        List<Member> members = mapper.mySelect2(param);
        System.out.println(members);
    }

    @Test
    public void associateSelect() {

        Member members = mapper.associateSelect(1182109448340652035L);
        System.out.println(members);
    }


    @Test
    public void wrapper() {
        List<Member> lambdaMembers = mapper.selectList(new QueryWrapper<Member>().lambda().gt(Member::getId, 2L));
        lambdaMembers.forEach(System.out::println);
    }


}