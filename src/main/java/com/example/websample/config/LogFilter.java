package com.example.websample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //외부 -> filter (-> 처리 ->) filter -> 외부
        log.info("Hello Filter : " + Thread.currentThread());
        chain.doFilter(request,response);
        log.info("Bye Filter : " + Thread.currentThread());
    }
}
