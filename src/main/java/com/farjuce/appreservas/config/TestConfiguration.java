package com.farjuce.appreservas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity web) -> web.ignoring().antMatchers("/**");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST,"/**").permitAll();
        return http.build();

}