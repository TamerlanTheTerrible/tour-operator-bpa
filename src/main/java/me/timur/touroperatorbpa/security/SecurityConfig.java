package me.timur.touroperatorbpa.security;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.security.jwt.JwtVerifierFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Temurbek Ismoilov on 06/08/23.
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtVerifierFilter jwtVerifierFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterAfter(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/sign-in").permitAll()

                .requestMatchers("/api/v1/accommodation/**").hasAnyAuthority("ADMIN", "MANAGER_BOOKING")
                .requestMatchers(HttpMethod.GET, "/api/v1/accommodation/**").hasAnyAuthority("TOUR_OPERATOR", "ACCOUNTANT")

                .requestMatchers("/api/v1/company/**").hasAnyAuthority("ADMIN", "TOUR_OPERATOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/company/**").hasAnyAuthority("GENERAL_MANAGER", "ACCOUNTANT")

                .requestMatchers("/api/v1/user/create", "/api/v1/user/*/status/*").hasAnyAuthority("ADMIN", "GENERAL_MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/v1/user/*").hasAnyAuthority("ADMIN", "GENERAL_MANAGER", "TOUR_OPERATOR", "ACCOUNTANT")
                .requestMatchers("/api/v1/user/*/change-password", "/api/v1/user/update").hasAnyAuthority("TOUR_OPERATOR")

                .requestMatchers("/api/v1/company/create", "/api/v1/company/update").hasAnyAuthority("ADMIN", "GENERAL_MANAGER", "TOUR_OPERATOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/company/**").permitAll()

                .requestMatchers("/api/v1/group/**").hasAnyAuthority("TOUR_OPERATOR")
                .requestMatchers("/api/v1/group/filter").hasAnyAuthority("GENERAL_MANAGER", "TOUR_OPERATOR")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }
}
