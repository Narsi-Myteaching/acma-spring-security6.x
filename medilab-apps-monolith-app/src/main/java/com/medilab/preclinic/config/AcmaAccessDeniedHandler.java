package com.medilab.preclinic.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class AcmaAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("Executing AccessDeniedHandler....");
        String reqUrl = request.getRequestURL().toString(); //http://localhost:8087/departments/addDept
        System.out.println("Incoming Request URL.."+reqUrl);
        if(StringUtils.hasText(reqUrl)){
            reqUrl = "http://localhost:8087/AuthzError";
            System.out.println("Redirect Error Url is "+reqUrl);
            response.sendRedirect(reqUrl);
//            if(reqUrl.contains("departments")){
//                response.setHeader("Location", reqUrl);
//                //reqUrl = reqUrl+"/AuthzError";  //http://localhost:8087/departments/AuthzError"
//                reqUrl = "http://localhost:8087/departments/AuthzError";
//                System.out.println("Redirect Error Url is "+reqUrl);
//                response.sendRedirect(reqUrl);
//            }
//            if(reqUrl.contains("doctors")){
//                //response.setHeader("Location", reqUrl);
//                //reqUrl = reqUrl+"/AuthzError";  //http://localhost:8087/departments/AuthzError"
//                reqUrl = "http://localhost:8087/doctors/AuthzError";
//                System.out.println("Redirect Error Url is "+reqUrl);
//                response.sendRedirect(reqUrl);
//            }
//            reqUrl = reqUrl.substring(0, reqUrl.lastIndexOf("/")); //http://localhost:8087/departments
//            response.setHeader("Location", reqUrl);
//            reqUrl = reqUrl+"/AuthzError";  //http://localhost:8087/departments/AuthzError"
//            System.out.println("Redirect Error Url is "+reqUrl);
//            response.sendRedirect(reqUrl);
        }else{
            System.out.println("No Un Authorized Req URL FOUND");
        }
    }
}
