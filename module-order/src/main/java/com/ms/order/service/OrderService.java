package com.ms.order.service;

import com.ms.core.kit.BaseService;
import com.ms.order.dao.OrderRepository;
import com.ms.order.entity.Order;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 9:02
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public interface OrderService extends BaseService<OrderRepository, Order> {


    /**
     * 提交订单
     * @param
     * @return com.ms.order.entity.Order
     * @author Zhu Kaixiao
     * @date 2019/8/29 9:13
     **/
    Order submitOrder(Order dto);
}
