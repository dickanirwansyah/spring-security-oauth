package com.spring.app.oauth2resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController {

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtractInfo(Authentication authentication){
        OAuth2AuthenticationDetails oauth2details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> details = (Map<String, Object>) oauth2details.getDecodedDetails();
        System.out.println("User organization is "+details.get("organization"));
        return details;
    }
}
