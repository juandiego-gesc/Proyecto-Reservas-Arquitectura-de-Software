package com.farjuce.appreservas.basicAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;


@Configuration
@Profile("test")
public class TestConfiguration {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring().antMatchers( "/**");

    }

}