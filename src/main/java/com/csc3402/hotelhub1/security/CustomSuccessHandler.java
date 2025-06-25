package com.csc3402.hotelhub1.security;

import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest req,
            HttpServletResponse res,
            Authentication auth) throws IOException {

        boolean isStaff = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STAFF"));

        String target = isStaff
                ? "/staff/dashboard"
                : "/customer/dashboard";

        res.sendRedirect(req.getContextPath() + target);
    }
}
