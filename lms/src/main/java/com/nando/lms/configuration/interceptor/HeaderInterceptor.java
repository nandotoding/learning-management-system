package com.nando.lms.configuration.interceptor;

import com.nando.lms.controller.UrlMapping;
import com.nando.lms.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    @Autowired
    public HeaderInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains(UrlMapping.AUTH)) {
            return true;
        }

        return jwtUtil.validateToken(request.getHeader("Authorization"));
    }
}
