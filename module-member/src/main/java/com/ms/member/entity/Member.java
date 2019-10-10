package com.ms.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ms.core.kit.BaseEntity;
import com.ms.role.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:13
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@ApiModel(value = "会员类")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Member extends BaseEntity<Long> {

    @ApiModelProperty(value = "会员用户名", example = "zhangsan")
    @NotBlank
    private String username;

    @ApiModelProperty(value = "会员密码", example = "123456")
    @NotBlank
    private String password;

    @ApiModelProperty(hidden = true)
    private String salt;

    @TableField(exist = false)
    private List<Role> roles;
}
