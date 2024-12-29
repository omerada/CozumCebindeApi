package com.omerada.cozumcebinde.core.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class ResponseBodyInterceptor implements ResponseBodyAdvice<Object> {

    @Autowired
    LoggingService loggingService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
public Object beforeBodyWrite(Object body, MethodParameter returnType,
                              MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                              ServerHttpRequest request, ServerHttpResponse response) {
    if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) response;
        loggingService.displayResp((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, body);
    }
    return body;
}
}