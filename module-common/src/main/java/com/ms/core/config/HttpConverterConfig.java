package com.ms.core.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.ms.core.utils.CglibUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
public class HttpConverterConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        // 统一把null转为空字符串
        ValueFilter nullFilter = (Object object, String name, Object v) -> Optional.ofNullable(v).orElse("");
        // 统一为分页数据加上序号
        ValueFilter pageSerialNumberFilter = new PageSerialNumberFilter();
        fastJsonConfig.setSerializeFilters(nullFilter, pageSerialNumberFilter);
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }


    private static class PageSerialNumberFilter implements ValueFilter {

        @Override
        public Object process(Object object, String name, Object value) {
            if (object instanceof IPage && "records".equals(name)) {
                IPage page = (IPage) object;
                List records = page.getRecords();
                long current = page.getCurrent();
                long size = page.getSize();
                String snFieldName = "pageSerialNumber";
                Map<String, Long> map = MapUtil.of(snFieldName, (current - 1) * size);
                for (int i = 0; i < records.size(); i++) {
                    Object originObj = records.get(i);
                    map.put(snFieldName, map.get(snFieldName) + 1);
                    Object target = CglibUtils.dynamicField(originObj, map);
                    records.set(i, target);
                }
            }

            return value;
        }
    }

}
