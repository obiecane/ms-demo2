package com.ms.core.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.util.Map;


public class CglibUtils {


    /**
     * 动态添加类的属性
     * @param dest
     * @param addProperties
     * @return
     * @author Zhu Kaixiao
     * @date 2019
     */
    public static Object dynamicField(Object dest, Map<String, ?> addProperties) {
        PropertyDescriptor[] descriptors = BeanUtil.getPropertyDescriptors(dest.getClass());
        Map<String, Class> propertyMap = Maps.newHashMapWithExpectedSize(descriptors.length);
        for(PropertyDescriptor d : descriptors) {
            if(!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // add extra properties
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // new dynamic bean
        DynamicBean dynamicBean =new DynamicBean(dest.getClass(), propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try{
                // filter extra properties
                if(!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, BeanUtil.getProperty(dest, k));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        // add extra value
        addProperties.forEach((k, v) -> {
            try{
                dynamicBean.setValue(k, v);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }

    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator =new BeanGenerator();
            if(null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {

        @JSONField(serialize = false)
        private String name;
        private String email;
    }


    public static void main(String[] args) throws Exception{
        Student student = Student.builder().name("jack").email("xy123zk@163.com").build();
        System.out.println(student.toString());
        Map<String,Object> properties = Maps.newHashMap();
        properties.put("address","浙江杭州");
        properties.put("age",26);

        Object target = dynamicField(student, properties);
        String json = JSONObject.toJSONString(target);
        System.out.println(json);
    }
}
