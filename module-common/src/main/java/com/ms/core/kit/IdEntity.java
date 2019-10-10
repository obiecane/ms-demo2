package com.ms.core.kit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/10/10 13:36
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Data
public abstract class IdEntity<ID> {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private ID id;
}
