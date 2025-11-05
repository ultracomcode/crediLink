package com.main.CrediLink.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfigurations(SecurityFilter securityFilter, CorsConfigurationSource corsConfigurationSource) {
        this.securityFilter = securityFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

//                ## Descomentar para teste localhost
//                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/login","/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/me").authenticated()

                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/webhook/notify/pix").permitAll()

                        .requestMatchers(HttpMethod.POST, "/pix/create","/pix/cancel/*","/pix/expired/*").hasAnyRole("USER")

                        .requestMatchers(HttpMethod.GET, "/users/accountcodes").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pix","/pix/*").hasAnyRole( "USER")

                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
