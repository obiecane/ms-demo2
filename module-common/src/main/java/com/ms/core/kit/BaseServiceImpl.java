package com.ms.core.kit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/28 16:35
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>
        implements BaseService<T> {

    @Override
    public IPage<T> page(IPage<T> page) {
        if (page instanceof PageParam) {
            List<PageParam.Sort> sorts = ((PageParam<T>) page).getSorts();
            QueryWrapper<T> queryWrapper = Wrappers.query();
            for (PageParam.Sort sort : sorts) {
                if (sort.isAsc()) {
                    queryWrapper = queryWrapper.orderByAsc(sort.getOrderBy());
                } else {
                    queryWrapper = queryWrapper.orderByDesc(sort.getOrderBy());
                }
            }
            return super.page(page, queryWrapper);
        } else {
            return super.page(page);
        }
    }
}
