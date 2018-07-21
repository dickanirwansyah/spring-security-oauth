package com.spring.oauth2.oauth2authorizationserver.security.controller;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class TokenController {

    @Resource(name = "tokenServices")
    ConsumerTokenServices consumerTokenServices;

    @Resource(name = "tokenStore")
    TokenStore tokenStore;

    @GetMapping(value = "/tokens")
    @ResponseBody
    public List<String> getTokens(){
        List<String> tokenValues = new ArrayList<String>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("fooClientIdPassword");
        if (tokens!=null){
            for(OAuth2AccessToken token:tokens){
                tokenValues.add(token.getValue());
            }
        }
        return tokenValues;
    }

    @PostMapping(value = "/oauth/token/revokeTokenById/{tokenId}")
    @ResponseBody
    public void revokeToken(@PathVariable String tokenId, HttpServletRequest request){
        consumerTokenServices.revokeToken(tokenId);
    }
}
