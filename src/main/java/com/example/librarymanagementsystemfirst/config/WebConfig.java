package com.example.librarymanagementsystemfirst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // applies CORS to all api endpoints
                .allowedOrigins("*") // allow request from any origin
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*") // allow all headers
                .allowCredentials(false);
    }
}
