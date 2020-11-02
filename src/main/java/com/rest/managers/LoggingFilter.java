package com.rest.managers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {

            if(((HttpServletRequest) request).getMethod().equals("POST")) {
                String requestBody = new String(requestWrapper.getContentAsByteArray());
                System.out.println("Request body: " + requestBody);
            }
            else {
                String responseBody = new String(responseWrapper.getContentAsByteArray());
                System.out.println("Response body: " + responseBody);
            }
            // Do not forget this line after reading response content or actual response will be empty!
            responseWrapper.copyBodyToResponse();

            // Write request and response body, headers, timestamps etc. to log files

        }

    }

}