package ru.lyudofa.srpringcourse.gymbro.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.lyudofa.srpringcourse.gymbro.security.TokenAuthFilter;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenAuthFilter tokenAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // (1) Отключаем CSRF (для API обычно не нужен)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/login").permitAll()
                        .requestMatchers("/users/profile").permitAll()
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/workouts").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(tokenAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}