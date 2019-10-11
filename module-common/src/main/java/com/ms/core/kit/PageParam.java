package com.ms.core.kit;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

class PageParam<T> extends Page<T> {

    @Setter
    @Getter
    @JSONField(serialize = false)
    private List<Sort> sorts = Collections.emptyList();


    @Data
    static class Sort {
        private String orderBy;
        private boolean asc;

        static Sort parseSort(String sortStr) {
            String[] strings = sortStr.trim().toLowerCase().split(" +");
            if (strings.length != 2) {
                throw new IllegalArgumentException("只支持[properties asc|desc]格式");
            }
            if ("asc".equals(strings[1])) {
                Sort sort = new Sort();
                sort.setOrderBy(strings[0]);
                sort.setAsc(true);
                return sort;
            } else if ("desc".equals(strings[1])) {
                Sort sort = new Sort();
                sort.setOrderBy(strings[0]);
                sort.setAsc(false);
                return sort;
            } else {
                throw new IllegalArgumentException("只支持[properties asc|desc]格式");
            }
        }
    }

}
