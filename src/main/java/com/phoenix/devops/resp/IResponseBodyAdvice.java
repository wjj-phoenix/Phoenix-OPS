package com.phoenix.devops.resp;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.devops.lang.Result;

@RestControllerAdvice(basePackages = "com.phoenix.devops.controller")
public class IResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, @Nullable MethodParameter parameter, @Nullable MediaType type,
            @Nullable Class<? extends HttpMessageConverter<?>> converterType, @Nullable ServerHttpRequest request,
            @Nullable ServerHttpResponse response) {
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
        // 如果返回值是String类型，那就手动把Result对象转换成JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }

    @Override
    public boolean supports(@Nullable MethodParameter returnType,
            @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
