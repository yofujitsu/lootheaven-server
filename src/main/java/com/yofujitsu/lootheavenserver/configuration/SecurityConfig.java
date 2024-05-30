package com.yofujitsu.lootheavenserver.configuration;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Autowired
    private CustomAuthenticationHandler customAuthenticationHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors((cors) -> cors
                        .configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.
                        requestMatchers("/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/loots/**").permitAll()
                                .requestMatchers("/users/**").permitAll()
                                .requestMatchers("/admin/**").permitAll()
                                .requestMatchers("/purchase/**").permitAll()
                                .requestMatchers("/auth/status").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                    oauth2Login -> oauth2Login
                            .successHandler(customAuthenticationHandler)
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://147.45.246.193:5173")); // Точно указываем домен, с которого разрешен доступ
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PATCH", "HEAD", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "Authorization", "Accept", "Origin"));

        // Эта настройка добавляет нужные заголовки в ответ сервера, позволяя браузеру процессить ответ.
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Length", "X-Total-Count"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

