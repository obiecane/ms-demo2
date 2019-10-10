package com.ms.member.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ms.core.kit.BaseController;
import com.ms.core.kit.JcResult;
import com.ms.member.service.MemberService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.ms.member.entity.Member;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 17:37
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Api(tags = "会员信息")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController extends BaseController {

    private final MemberService memberService;


    @ApiOperation(value = "新增会员信息")
    @PostMapping("")
    public Member addMember(@Valid @RequestBody Member member) {
        memberService.save(member);
        return member;
    }

    @ApiOperation(value = "获取会员信息")
    @GetMapping("/{id}")
    public Member getMember(@PathVariable long id) {
        Member member = memberService.getById(id);
        return member;
    }

    @ApiOperation(value = "删除会员信息")
    @DeleteMapping("/{id}")
    public JcResult deleteMember(@PathVariable long id) {
        boolean s = memberService.removeById(id);
        return JcResult.builder().data(s).build();
    }

    @ApiOperation(value = "修改会员信息")
    @PutMapping("")
    public Member modifyMember(@RequestBody Member member) {
        memberService.updateById(member);
        return member;
    }

    @ApiOperation(value = "校验会员信息")
    @GetMapping("/valid/{id}")
    public JcResult validMember(@PathVariable long id) {
        boolean v = memberService.validMember(id);
        return JcResult.ok("校验成功", v);
    }


    @ApiOperation(value = "校验会员信息")
    @GetMapping("/pages")
    public JcResult<IPage<Member>> page(IPage<Member> page) {
        IPage<Member> page1 = memberService.page(page);
        return JcResult.okData(page1);
    }
}
