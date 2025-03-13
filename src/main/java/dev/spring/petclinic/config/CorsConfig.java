package dev.spring.petclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // 모든 API에 대해 CORS 허용
                        .allowedOrigins("http://localhost:3000") // Next.js 프론트엔드 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메서드
                        .allowCredentials(true);
            }
        };
    }
}
