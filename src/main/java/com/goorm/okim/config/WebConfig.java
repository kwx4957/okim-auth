package com.goorm.okim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";

    @Value("${front.url.http}")
    private String frontHttp;

    @Value("${front.url.https}")
    private String frontHttps ;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

                // (CORS) 아래의 주소에 대해서만 접근을 허용합니다.
                .allowedOrigins(
                        frontHttp,
                        frontHttps,
                        HTTP_LOCALHOST_8080,
                        "https://www.oh-kim.com"
                )
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
