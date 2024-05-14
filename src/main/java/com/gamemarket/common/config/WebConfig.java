package com.gamemarket.common.config;

import com.gamemarket.common.interceptor.AuthInterceptor;
import com.gamemarket.common.interceptor.TimeCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final TimeCheckInterceptor timeCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/sign-up", "/user/sign-in");

        registry.addInterceptor(timeCheckInterceptor)
                .addPathPatterns("/**");
    }

}
