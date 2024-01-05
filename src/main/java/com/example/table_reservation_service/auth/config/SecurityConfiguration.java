package com.example.table_reservation_service.auth.config;

import com.example.table_reservation_service.auth.security.JwtAccessDeniedHandler;
import com.example.table_reservation_service.auth.security.JwtAuthenticationEntryPoint;
import com.example.table_reservation_service.auth.security.JwtAuthenticationFilter;
import com.example.table_reservation_service.auth.security.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeHttpRequests()
                    .antMatchers("/api/**/", "exception/**/").permitAll()
                    .antMatchers("/api/partner/**").hasRole("PARTNER")
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증 예외 처리
                    .accessDeniedHandler(jwtAccessDeniedHandler) //인가(권한) 예외 처리
                .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
