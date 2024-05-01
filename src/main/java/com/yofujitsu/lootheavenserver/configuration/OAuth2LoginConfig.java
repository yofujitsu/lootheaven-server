package com.yofujitsu.lootheavenserver.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.DefaultClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class OAuth2LoginConfig {

    private static class CustomRequestEntityConverter implements Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {
        @Override
        public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest source) {
            // Implement conversion logic here
            // You need to ensure that the client ID and secret are included in the POST body as per the 'client_secret_post' requirement
            return RequestEntity
                    .post(URI.create(source.getClientRegistration().getProviderDetails().getTokenUri()))
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .body("client_id=" + source.getClientRegistration().getClientId() +
                                    "&client_secret=" + source.getClientRegistration().getClientSecret()
                            // Add other required parameters here
                    );
        }
    }
}
