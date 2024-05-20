package com.gamemarket.common.config;

import com.gamemarket.user.domain.CurrentUserArgumentResolver;
import com.gamemarket.common.interceptor.AuthInterceptor;
import com.gamemarket.common.interceptor.TimeCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final TimeCheckInterceptor timeCheckInterceptor;
    private final CurrentUserArgumentResolver currentUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/sign-up", "/user/sign-in")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**");

        registry.addInterceptor(timeCheckInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver);
    }

}
