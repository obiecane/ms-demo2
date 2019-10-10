package com.ms.member.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.member.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
    public void test() {
        List<Member> lambdaUsers = memberService.list(new QueryWrapper<Member>().lambda().gt(Member::getId, 2L));
        print(lambdaUsers);
    }

    @Test
    public void aSave() {
        Member member = new Member();
        member.setUsername("人海");
        member.setPassword("password");
        boolean success = memberService.save(member);
        assertTrue(success);
    }

    @Test
    public void aUpdate() {
        Member member = new Member();
        member.setId(1182109448340652035L);
        member.setUsername("人设");
        member.setPassword("password333");
        boolean success = memberService.updateById(member);
        assertTrue(success);
    }

    @Test
    public void aUpdateBatch() {
        Member member1 = new Member();
        member1.setId(1182109448340652035L);
        member1.setUsername("人设");
        member1.setPassword("password333333");

        Member member2 = new Member();
        member2.setId(1182109448340652034L);
        member2.setUsername("你再项目");
        member2.setPassword("password45646");

        List<Member> list = Arrays.asList(member1, member2);
        boolean success = memberService.updateBatchById(list);
        assertTrue(success);
    }

    @Test
    public void aSaveOrUpdate() {
        Member member1 = new Member();
        member1.setId(1182109448340652035L);
        member1.setUsername("人设");
        member1.setPassword("password35553");

        boolean success = memberService.saveOrUpdate(member1);
        assertTrue(success);
    }

    @Test
    public void aPage() {
        IPage<Member> page = new Page<>();
        page.setCurrent(2);
        page.setSize(2);
        IPage<Member> page1 = memberService.page(page);
        assertEquals(page, page1);
        System.out.println("总条数: " + page1.getTotal());
        System.out.println("总页数: " + page1.getPages());
        System.out.println("当前页码: " + page1.getCurrent());
        System.out.println("页码大小: " + page1.getSize());
        print(page1);
    }

    private void print(IPage page) {
        Optional
                .ofNullable(page)
                .map(IPage::getRecords)
                .orElse(Collections.emptyList())
                .forEach(System.out::println);
    }

    private void print(List list) {
        Optional.ofNullable(list).orElse(Collections.emptyList())
                .forEach(System.out::println);
    }
}