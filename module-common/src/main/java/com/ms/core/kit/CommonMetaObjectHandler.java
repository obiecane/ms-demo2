package com.ms.core.kit;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/10/10 13:45
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createUser")) {
            this.setInsertFieldValByName("createUser", "自动填充用户名", metaObject);
        }
        if (metaObject.hasSetter("createTime")) {
            this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateUser")) {
            this.setUpdateFieldValByName("updateUser", "自动填充用户名", metaObject);
        }
        if (metaObject.hasSetter("updateTime")) {
            this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
