package com.ms.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ms.core.kit.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhu Kaixiao
 * @date 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("jc_role")
@ApiModel(value="Role对象")
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = 644638380928088860L;

    @ApiModelProperty(value = "角色名称")
    private String name;


}
