package bsm.choi.fancafe.global.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(withDefaults()); // LoginForm 사용



        return http.build();
    }
}