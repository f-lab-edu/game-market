package com.gamemarket.common.interceptor;

import com.gamemarket.common.utils.SessionUtil;
import com.gamemarket.user.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final User user = SessionUtil.getUserFromSession(request.getSession());
        request.setAttribute("user", user);

        return true;
    }

}
