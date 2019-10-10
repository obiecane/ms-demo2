package com.ms.order.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:17
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Entity
@Data
@Table(name = "jc_order")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String orderNo;


    private Long memberId;


    private String description;
}
