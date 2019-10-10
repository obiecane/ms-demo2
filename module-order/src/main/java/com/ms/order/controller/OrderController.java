package com.ms.order.controller;

import com.ms.order.entity.Order;
import com.ms.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 9:04
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Api(tags = "订单信息")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;


    @ApiOperation(value = "新增订单信息")
    @PostMapping("")
    public Order addMember(@RequestBody Order order) {
        order = orderService.submitOrder(order);
        return order;
    }

    @ApiOperation(value = "获取订单信息")
    @GetMapping("/{id}")
    public Order getMember(@PathVariable long id) {
        Order order = orderService.getById(id);
        return order;
    }

    @ApiOperation(value = "删除订单信息")
    @DeleteMapping("/{id}")
    public Order deleteMember(@PathVariable long id) {
        Order order = orderService.removeById(id);
        return order;
    }

    @ApiOperation(value = "修改订单信息")
    @PutMapping("")
    public Order modifyMember(@RequestBody Order member) {
        orderService.modify(member);
        return member;
    }

}
