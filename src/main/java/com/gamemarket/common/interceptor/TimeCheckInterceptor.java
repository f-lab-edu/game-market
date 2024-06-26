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
    private static final String REQUEST_START_TIME = "start";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long processTime = getProcessTime(request);

        if (processTime > LATENCY) {
            latencyProcess(request, response, processTime);
        } else {
            normalProcess(request, response, processTime);
        }
    }

    private long getProcessTime(HttpServletRequest request) {
        long requestEndTime = System.currentTimeMillis();
        long requestStartTime = (long) request.getAttribute(REQUEST_START_TIME);

        return requestEndTime - requestStartTime;
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
