package com.medilab.preclinic.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AcmaVistorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("**********Executing AcmaVistorsFilter*********");
         boolean isNewSession =  request.getSession().isNew();
         System.out.println("Is New Session "+isNewSession);
        try{
            filterChain.doFilter(request,response);
        }catch (Exception exception){
            String loc = "http://localhost:8087/AuthzError";
            response.sendRedirect(loc);
        }
    }
}
