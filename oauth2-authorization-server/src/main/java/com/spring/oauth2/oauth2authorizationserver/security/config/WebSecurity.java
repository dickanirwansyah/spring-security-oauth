package com.spring.oauth2.oauth2authorizationserver.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //test data
    @Autowired
    public void globalUsersDetail(AuthenticationManagerBuilder builder) throws Exception{
        builder.inMemoryAuthentication()
                .withUser("dickanirwansyah").password(bCryptPasswordEncoder.encode("root")).roles("ADMIN")
                    .and()
                .withUser("adihidayat").password(bCryptPasswordEncoder.encode("root")).roles("USER")
                    .and()
                .withUser("fitrah").password(bCryptPasswordEncoder.encode("root")).roles("USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(final HttpSecurity security) throws Exception{
        security.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/token/revokeById/**").permitAll()
                .antMatchers("/tokens/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().csrf().disable();
    }

}
