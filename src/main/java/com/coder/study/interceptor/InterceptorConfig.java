package com.coder.study.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象并指定拦截的路径
        registry.addInterceptor(new MemberInterceptor())
             
                .excludePathPatterns("/frontdesk/register_ok")
                .excludePathPatterns("/frontdesk/register")
                .excludePathPatterns("/frontdesk/student/register")
                .excludePathPatterns("/frontdesk/student_login")
                .excludePathPatterns("/frontdesk/student/login")
                .excludePathPatterns("/frontdesk/teacher_login")
                .excludePathPatterns("/frontdesk/teacher/login")
                .addPathPatterns("/frontdesk/**")
                .excludePathPatterns("/**/*.css","/**/*.js","/frontdesk/images/*.jpg")
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.img")

        ;

    }
}
