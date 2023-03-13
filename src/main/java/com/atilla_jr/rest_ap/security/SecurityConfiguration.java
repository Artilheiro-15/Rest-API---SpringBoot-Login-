package com.atilla_jr.rest_ap.security;

import com.atilla_jr.rest_ap.exception.JwtExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  @Autowired
  private JwtAuthenticationFilter jwtAuthFilter;

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private JwtExceptionHandler jwtExceptionHandler;

  // @Bean
  // public Http401AuthenticationEntryPoint securityException401EntryPoint() {
  //   return new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\"");
  // }

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf()
      .disable()
      .authorizeHttpRequests()
      .requestMatchers("/auth/**")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .exceptionHandling()
      .authenticationEntryPoint(jwtExceptionHandler);

    return http.build();
  }
}
