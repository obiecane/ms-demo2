package com.ms.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.ms.core.kit.BaseServiceImpl;
import com.ms.order.dao.OrderRepository;
import com.ms.order.entity.Order;
import com.ms.order.service.MemberService;
import com.ms.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 9:03
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<OrderRepository, Order>
        implements OrderService {

    private final MemberService memberService;

    @Override
    @Transactional
    @LcnTransaction
    public Order submitOrder(Order order) {

        if (memberService.validMember(order.getMemberId())) {
            Order add = add(order);
            if (add.getId() != 1) {
//                throw new IllegalArgumentException("异常回滚");
            }
        }
        return order;
    }
}
