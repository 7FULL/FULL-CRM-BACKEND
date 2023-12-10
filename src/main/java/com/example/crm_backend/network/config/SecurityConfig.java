package com.example.crm_backend.network.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Estas son las contraseñas hasheadas con el BCryptEncoder son (user y admin)
    final String ADMIN_PASSWORD = "$2a$10$beMaPHqnbN5xKb6H.OfC/OLvlTAWVB5mwTFsldIy7I1RbrnJllGl6";

    final String USER_PASSWORD = "$2a$10$p8IFPSHfqARXuCX2IjqItezvcaq42Ak6Hj6I/ivTlWdMdj1GGjT96";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/doc").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors().disable()
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
