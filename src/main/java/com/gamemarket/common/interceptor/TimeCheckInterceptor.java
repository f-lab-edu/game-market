package com.gamemarket.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TimeCheckInterceptor implements HandlerInterceptor {

    private static final long LATENCY = 2000;

    private long start;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long processTime = end - start;

        if (processTime > LATENCY) {
            latencyProcess(request, response, processTime);
        } else {
            normalProcess(request, response, processTime);
        }
    }

    private void latencyProcess(HttpServletRequest request, HttpServletResponse response, long processTime) {
        log.warn("[{} {}] {} | Latency Time: {}(ms)",
                response.getStatus(),
                request.getRequestURI(),
                request.getMethod(),
                processTime);
    }

    private static void normalProcess(HttpServletRequest request, HttpServletResponse response, long processTime) {
        log.info("[{} {}] {} | Response Time: {}(ms)",
                response.getStatus(),
                request.getMethod(),
                request.getRequestURI(),
                processTime);
    }

}
