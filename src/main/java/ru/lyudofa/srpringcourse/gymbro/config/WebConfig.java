package ru.lyudofa.srpringcourse.gymbro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // теперь метод существует
                .allowedOrigins("*") // или укажи конкретный frontend origin
                .allowedMethods("*") // GET, POST, PUT, DELETE и т.д.
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
