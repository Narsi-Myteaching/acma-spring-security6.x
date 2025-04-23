package com.medilab.preclinic.rest;

import com.medilab.preclinic.bean.AcmaUser;
import com.medilab.preclinic.config.AcmaJwtTokenManagerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AcmaApiAuthenticator {

    @Autowired
    private AcmaJwtTokenManagerService jwtTokenManagerService;

//    @PostMapping(value = {"/authenticate"})
//    public Map<String, Object> authenticate(@RequestBody AcmaUser user) {
//        String token = jwtTokenManagerService.generateToken(user.getUserName());
//        Map<String, Object> map = new HashMap<>();
//        map.put("access_token",token);
//        map.put("token_type","Bearer");
//        map.put("expiry",AcmaJwtTokenManagerService.EXPIRY);
//
//        return map;
//    }

    @GetMapping(value = {"/authenticate"})
    public Map<String, Object> authenticate(HttpServletRequest request) {
        String basicAuthHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        basicAuthHeader =  basicAuthHeader.replace(HttpServletRequest.BASIC_AUTH, "");
        String decodedString =  Base64.getDecoder().decode(basicAuthHeader.getBytes()).toString();
        String userName = decodedString.split(":")[0];
        String token = jwtTokenManagerService.generateToken(userName);
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",token);
        map.put("token_type","Bearer");
        map.put("expiry",AcmaJwtTokenManagerService.EXPIRY);

        return map;
    }
}
