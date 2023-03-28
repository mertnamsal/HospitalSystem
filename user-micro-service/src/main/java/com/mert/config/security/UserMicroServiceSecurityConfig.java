package com.mert.config.security;

import com.mert.utility.JwtTokenManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserMicroServiceSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
