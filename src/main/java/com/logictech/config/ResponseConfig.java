package com.logictech.config;


import java.lang.reflect.Type;

import com.github.pagehelper.PageInfo;
import com.logictech.entity.so.ResultEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author JG.Hannibal
 * @since 2017/11/10 下午12:17
 */
@ControllerAdvice
public class ResponseConfig implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Type type = returnType.getGenericParameterType();
        // 不进行包装的
        boolean noAware = ResultEntity.class.equals(type) || String.class.equals(type) || PageInfo.class.equals(type);
        return !noAware;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        ResultEntity result = new ResultEntity(body);
        return result;
    }
}
    