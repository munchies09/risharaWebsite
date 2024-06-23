package com.csc3402.security.formlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SpringSecurity {
    @Bean
    public static PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/home/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form)-> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home",true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout)->logout.permitAll())
                .exceptionHandling((exceptionHandling)->exceptionHandling.accessDeniedPage("/access-denied"))
                .csrf((csrf)->csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console**")))
                .headers((headers)->headers.disable());
        return http.build();
    }
}
