package com.gabriel.UaiCores_ProductionLine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/api/v1/order").permitAll();
                           authorizeConfig.requestMatchers(HttpMethod.GET, "/api/v1/order/{id}").permitAll();
                           authorizeConfig.requestMatchers(HttpMethod.GET, "/api/v1/task").permitAll();
                           authorizeConfig.requestMatchers(HttpMethod.GET, "/api/v1/task/{id}").permitAll();
                           authorizeConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/task/{id}").permitAll();
                           authorizeConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/order/{id}").permitAll();
                           authorizeConfig.requestMatchers("/logout").permitAll();
                           authorizeConfig.anyRequest().authenticated();
                        }
                ).oauth2Login(Customizer.withDefaults())
                .build();
    }
}
