package com.example.crm_backend.network.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Spring Security configuration
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    //Estas son las contraseñas hasheadas con el BCryptEncoder son (user y admin)
    final String ADMIN_PASSWORD = "$2a$10$beMaPHqnbN5xKb6H.OfC/OLvlTAWVB5mwTFsldIy7I1RbrnJllGl6";

    final String USER_PASSWORD = "$2a$10$p8IFPSHfqARXuCX2IjqItezvcaq42Ak6Hj6I/ivTlWdMdj1GGjT96";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/doc").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password(USER_PASSWORD).roles("USER")
                .and()
                .withUser("admin").password(ADMIN_PASSWORD).roles("ADMIN", "USER");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
