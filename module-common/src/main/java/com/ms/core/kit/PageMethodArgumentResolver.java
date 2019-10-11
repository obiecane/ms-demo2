package com.ms.core.kit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.core.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        int pageNo = 1;
        int pageSize = 10;
        // 在此处通过request.getParameter()方式获取分页所需参数
        // 获取页码
        String pageNoParam = webRequest.getParameter("pageNo");
        if (StringUtils.isNotBlank(pageNoParam)) {
            try {
                pageNo = Integer.parseInt(pageNoParam);
            } catch (NumberFormatException ignore) { }
        }
        // 获取分页尺寸
        String pageSizeParam = webRequest.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSizeParam)) {
            try {
                pageSize = Integer.parseInt(pageSizeParam);
            } catch (NumberFormatException ignore) { }
        }

        String pageSortParam = webRequest.getParameter("pageSort");
        if (StringUtils.isNotBlank(pageSortParam)) {
            try {
                List<PageParam.Sort> sorts = Arrays.stream(pageSortParam.split(","))
                        .map(PageParam.Sort::parseSort)
                        .collect(Collectors.toList());
                PageParam page = new PageParam();
                page.setCurrent(pageNo);
                page.setSize(pageSize);
                page.setSorts(sorts);
                return page;
            } catch (Exception ignore) {}
        }
        // 创建IPage实例
        IPage page = new Page(pageNo, pageSize);
        // 返回IPage实例，这个page就会以方法形参的方式传入控制器类的方法中
        return page;
    }
}
