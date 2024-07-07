package com.example.websample.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandler LogInterceptor : "+ Thread.currentThread());
        log.info("preHandler handler : " + handler);
        //요청이 더 진행되고싶으면 True, 아니면 False를 리턴
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        //어떤 모델 뷰를 제공해줌
        log.info("postHandler LogInterceptor : "+ Thread.currentThread());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion LogInterceptor : "+ Thread.currentThread());

        // ex가 있으면 에러 반환, 에러처리를 함
        if(ex != null){
            log.error("afterCompletion exception : "+handler);
        }
    }

}
