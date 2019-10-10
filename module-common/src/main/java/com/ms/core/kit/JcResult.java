package com.ms.core.kit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 15:13
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@ApiModel(value = "返回值包装类")
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JcResult<T> {

    @ApiModelProperty("状态代码")
    private int code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;


    public static <E> JcResult<E> ok(String message, E data) {
        return new JcResult<>(1, message, data);
    }

    public static <E> JcResult<E> okData(E data) {
        return ok("成功", data);
    }

    public static <E> JcResult<E> ok(String message) {
        return ok(message, null);
    }

    public static <E> JcResult<E> ok() {
        return okData(null);
    }


    public static <E> JcResult<E> fail(String message, E data) {
        return new JcResult<>(0, message, data);
    }

    public static <E> JcResult<E> failData(E data) {
        return fail("系统繁忙", data);
    }

    public static <E> JcResult<E> fail(String message) {
        return fail(message, null);
    }

    public static <E> JcResult<E> fail() {
        return failData(null);
    }
}
