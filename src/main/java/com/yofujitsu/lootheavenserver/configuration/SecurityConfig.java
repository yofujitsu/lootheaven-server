package com.yofujitsu.lootheavenserver.configuration;

import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .oidcUserService(this::processOidcUser)
                        )
                );
        return http.build();
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        Map<String, Object> attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("preferred_username");

        userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(name);
            newUser.setBalance(0L);
            newUser.setRole(UserRole.USER);
            newUser.setActive(true);
            newUser.setRegDate(LocalDate.now());
            userRepository.save(newUser);
            return newUser;
        });

        return oidcUser;
    }
}
