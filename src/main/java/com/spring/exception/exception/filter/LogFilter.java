package com.spring.exception.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;


@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest) request;
        String requestURO = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}] [{}] [{}]", uuid, request.getDispatcherType(), requestURO);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.info("Exception = {}",e);
            throw  e;
        }finally {
            log.info("RESPONSE [{}] [{}] [{}]", uuid, request.getDispatcherType(), requestURO);

        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
