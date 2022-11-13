package com.example.interceptor;

import com.example.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Login拦截器...");
        String token = request.getHeader("access-token");
        System.out.println("接收到的请求头token=" + token);
        boolean b = JWTUtils.verifyToken(token);
        if (b) {
            return true;
        } else {
            response.getWriter().write("Invalid Request!");
            return false;
        }
    }
}
