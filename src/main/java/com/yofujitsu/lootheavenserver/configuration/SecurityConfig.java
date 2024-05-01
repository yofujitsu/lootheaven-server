package com.yofujitsu.lootheavenserver.configuration;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Map;
import java.time.LocalDate;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/users/login", "/users/me").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/**", "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .oidcUserService(customOidcUserService())
                        )
                )
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/users/login")  // Custom login page that triggers OAuth2 login
//                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));

        return http.build();
    }

    public OAuth2UserService<OidcUserRequest, OidcUser> customOidcUserService() {
        return new OAuth2UserService<OidcUserRequest, OidcUser>() {
            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
                OidcUser oidcUser = (OidcUser) userRequest.getIdToken().getClaims();
                return processOidcUser(userRequest, oidcUser);
            }
        };
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

