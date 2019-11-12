package com.example.bikesharingapi.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            FirebaseToken userRecord = FirebaseAuth.getInstance().verifyIdToken(getAuthToken(((HttpServletRequest) request).getHeader("Authorization")));
            LOG.info("Successfully fetched user data {} : {} ", userRecord.getEmail(), userRecord.getName());
        } catch (FirebaseAuthException e) {
            LOG.error(e.getMessage());
            res.sendError(403);
        }

        LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        chain.doFilter(request, response);
        LOG.info("Logging Response :{}", res.getContentType());
    }

    private String getAuthToken(String header) {
        return header.split(" ")[1];
    }

}
