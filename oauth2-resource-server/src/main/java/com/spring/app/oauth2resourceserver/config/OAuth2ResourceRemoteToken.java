package com.spring.app.oauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceRemoteToken extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(final HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices(){
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:10000/spring-security-oauth-server/oauth/check_token");
        remoteTokenServices.setClientId("fooClientIdPassword");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }
}
