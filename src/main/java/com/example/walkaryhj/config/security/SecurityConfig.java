package com.example.walkaryhj.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests((authorizeRequests) ->
                    authorizeRequests
                            .requestMatchers("/", "/login", "/oauth/**").permitAll()
                            .anyRequest().authenticated()
            )

            .formLogin((formLogin) ->
                    formLogin
                            .loginPage("/login")
                            .permitAll()
            )
            .oauth2Login((oauth2Login) ->
                    oauth2Login
                            // 원래는 지정할 수 있어야 하지만 안됨!
                            // 시큐리티는 /login/oauth2/code/{registrationId} 패턴의 URL을 OAuth2 로그인 요청으로 인식한다.
                            // default :http://localhost:8080/login/oauth2/code/google
//                            .authorizationEndpoint((authorizationEndpoint) ->
//                                    authorizationEndpoint
//                                            .baseUri("/login/oauth2/code/mygoogle")
//                            )
                            .loginPage("/login")
                            .userInfoEndpoint(userInfoEndpoint ->
                                    userInfoEndpoint
                                            .userService(oauth2UserService())
                            ).successHandler(new CustomAuthenticationSuccessHandler())
            )

                .requestCache((requestCache) ->
                    requestCache.requestCache(new NullRequestCache())
            );

        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        // Implement your user service here
        return new DefaultOAuth2UserService();
    }

}

