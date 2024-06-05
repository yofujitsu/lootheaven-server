package com.yofujitsu.lootheavenserver.configuration;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole.*;

@Component
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    public CustomAuthenticationHandler() {
        super();  // Initialize with no default redirect path
        setDefaultTargetUrl("http:///147.45.246.193:5173/home");  // Ensure this is set if the URL is null
        setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User oidcUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("username");
        String avatar = (String) attributes.get("avatar");
        String userId = (String) attributes.get("id");

        String avatarUrl = String.format("https://cdn.discordapp.com/avatars/%s/%s", userId, avatar);

        User newUser = userRepository.findByEmail(email);
        if (newUser == null) {
            newUser = new User();
            newUser.setEmail(email);
            newUser.setActive(true);
            newUser.setBalance(0L);
            newUser.setDealsCount(0L);
            newUser.setOrdersCount(0L);
            newUser.setRole(USER);
            newUser.setDiscordId(userId);
            newUser.setRegDate(LocalDate.now());
            newUser.setUsername(username);
            newUser.setAvatar(avatarUrl);
        };


        userRepository.save(newUser);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
