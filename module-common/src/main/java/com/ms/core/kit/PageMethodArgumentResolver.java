package com.ms.core.kit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class PageMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 获取参数类型
        Class<?> paramType = parameter.getParameterType();
        // 如果该参数类型是PageBounds类自身或者子类，则返回true，表示这个解析器可以解析此类型
        return IPage.class.isAssignableFrom(paramType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 在此处通过request.getParameter()方式获取分页所需参数
        // 获取页码 这些字段可以通过自定义注解来指定
        int pageNo = Optional.ofNullable(webRequest.getParameter("pageNo")).map(Integer::valueOf).orElse(1);
        // 获取分页尺寸
        int pageSize = Optional.ofNullable(webRequest.getParameter("pageSize")).map(Integer::valueOf).orElse(10);
        // 创建IPage实例
        IPage page = new Page(pageNo, pageSize);
        // 返回IPage实例，这个page就会以方法形参的方式传入控制器类的方法中
        return page;
    }
}
