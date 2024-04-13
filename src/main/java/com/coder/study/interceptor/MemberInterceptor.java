package com.coder.study.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从session中获取用户信息
        Object studentInfo = request.getSession().getAttribute("studentInfo");
        Object teacherInfo = request.getSession().getAttribute("teacherInfo");


        if (null == studentInfo&&null == teacherInfo){
            response.sendRedirect("/frontdesk/student_login");
            return false;
        }
        return true;
    }
}
