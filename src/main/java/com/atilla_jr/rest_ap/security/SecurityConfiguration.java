package com.atilla_jr.rest_ap.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  @Autowired
  private JwtAuthenticationFilter jwtAuthFilter;

  private AuthenticationProvider authenticationProvider;

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf()
      .disable()
      .authorizeHttpRequests()
      .requestMatchers("/auth/**")
      .permitAll()
      // .requestMatchers("/questions/**").hasRole(Role.USER.toString())
      // .requestMatchers("/answers/**").hasRole(Role.USER.toString())
      // .requestMatchers("/users/**").hasRole(Role.USER.toString())
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
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    CorsConfiguration corsConfiguration = new CorsConfiguration()
      .applyPermitDefaultValues();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
  }
}
