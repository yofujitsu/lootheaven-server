package com.yofujitsu.lootheavenserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://147.45.246.193:5173") // Обеспечивает доступ только с этих доменов
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS", "HEAD") // Явно указывает, какие методы разрешены
                .allowedHeaders("Content-Type", "X-Requested-With", "Authorization", "Accept", "Origin") // Ограничивает, какие заголовки разрешено отправлять
                .allowCredentials(true); // Позволяет передавать учетные данные (куки, заголовки авторизации и т.д.)
    }

}