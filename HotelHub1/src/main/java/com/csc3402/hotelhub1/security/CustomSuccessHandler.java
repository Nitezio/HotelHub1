package com.csc3402.hotelhub1.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth)
            throws IOException, ServletException {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            response.sendRedirect("/staff/dashboard");
        } else {
            response.sendRedirect("/customer/dashboard");
        }
    }
}
