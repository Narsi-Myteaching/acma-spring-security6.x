package com.properties.acma.acma_authnz_service.resources;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AcmaTokenResource {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping(value = {"/acma/code"})
    public void getCode(HttpServletResponse response) throws IOException {
        response.sendRedirect("/acma/token");
    }

//    @GetMapping(value = {"/acma/token"})
//    public String getToken(OAuth2AuthenticationToken authentication, HttpServletResponse response) throws IOException {
//        System.out.println("i am in get Token Method");
//        String subjectId = authentication.getName();
//        String accessToken = null;
//        if (!StringUtils.hasText(subjectId)) {
//            response.sendRedirect("/acma/token");
//        } else {
//            System.out.println("Logged in User is " + subjectId);
//
//            OAuth2AuthorizedClient authorizedClient = authorizedClientService
//                    .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), subjectId);
//
//            accessToken = authorizedClient.getAccessToken().getTokenValue();
//            System.out.println("access Token " + accessToken);
//
//            OidcUser user = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            System.out.println("id Token " + user.getIdToken().getTokenValue());
//
//            Map<String, String> usersMap = new HashMap<>();
//            usersMap.put("access_token", accessToken);
//            usersMap.put("refresh_token", authorizedClient.getRefreshToken().getTokenValue());
//            usersMap.put("id_token", user.getIdToken().getTokenValue());
//            usersMap.put("token_type", authorizedClient.getAccessToken().getTokenType().getValue());
//            usersMap.put("token_expiry", authorizedClient.getAccessToken().getExpiresAt().toString());
//
//            JSONObject jsonObject = new JSONObject(usersMap);
//            System.out.println("json Object is " + jsonObject.toString());
//
//            return jsonObject.toString();
//        }
//        return  null;
//    }

    @GetMapping(value = {"/acma/token"})
    public void getToken(OAuth2AuthenticationToken authentication, HttpServletResponse response) throws IOException {
        System.out.println("i am in get Token Method");
        String subjectId = authentication.getName();
        String accessToken = null;
        if (!StringUtils.hasText(subjectId)) {
            response.sendRedirect("/acma/token");
        } else {
            System.out.println("Logged in User is " + subjectId);

            OAuth2AuthorizedClient authorizedClient = authorizedClientService
                    .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), subjectId);

            accessToken = authorizedClient.getAccessToken().getTokenValue();
            System.out.println("access Token " + accessToken);

            OidcUser user = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("id Token " + user.getIdToken().getTokenValue());

            Map<String, String> usersMap = new HashMap<>();
            usersMap.put("access_token", accessToken);
            usersMap.put("refresh_token", authorizedClient.getRefreshToken().getTokenValue());
            usersMap.put("id_token", user.getIdToken().getTokenValue());
            usersMap.put("token_type", authorizedClient.getAccessToken().getTokenType().getValue());
            usersMap.put("token_expiry", authorizedClient.getAccessToken().getExpiresAt().toString());

            JSONObject jsonObject = new JSONObject(usersMap);
            System.out.println("json Object is " + jsonObject.toString());

            Cookie cookie = new Cookie("acmaCk",accessToken);
            cookie.setPath("/");
            cookie.setDomain("localhost");

            response.addCookie(cookie);
            response.setHeader("Access-Control-Allow-Origin","true");

            response.sendRedirect("http://localhost:3000/dashboard");
        }
    }

}
