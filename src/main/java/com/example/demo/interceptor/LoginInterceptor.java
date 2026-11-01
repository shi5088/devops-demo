package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 如果 session 里没有 user 标记，说明没登录
        if (session.getAttribute("user") == null) {
            // 原来是：response.sendRedirect("/login");
// 改成：
            response.sendRedirect("/login?needLogin=true");
            return false; // 拦截，不放行
        }
        return true; // 放行
    }
}