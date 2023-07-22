package com.pinkieyun.fitnesscenter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.pinkieyun.fitnesscenter.Constants.API;
import com.pinkieyun.fitnesscenter.Constants.ROLE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        API.BASE_API + API.ORGANIZATIONS,
                        "/test/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/hello",
                        "/hello/**",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html")
                .permitAll()
                .requestMatchers(API.BASE_API + API.STAFFS + "/**").hasAnyAuthority(ROLE.ADMIN)
                .requestMatchers(API.BASE_API + API.EQUIPMENTS + "/**").hasAnyAuthority(ROLE.ADMIN)
                .requestMatchers(API.BASE_API + API.PACKS + "/**").hasAnyAuthority(ROLE.ADMIN)
                .requestMatchers(API.BASE_API + API.PAYMENTS).hasAnyAuthority(ROLE.SALE, ROLE.ADMIN)
                .requestMatchers(HttpMethod.GET, API.BASE_API + API.PAYMENTS + "/my-payment").hasAnyAuthority(ROLE.MEMBER)
                .requestMatchers(HttpMethod.GET, API.BASE_API + API.FEEDBACK + "/to-me").hasAnyAuthority(ROLE.SALE, ROLE.PT)
                .requestMatchers(HttpMethod.POST, API.BASE_API + API.FEEDBACK).hasAnyAuthority(ROLE.MEMBER)
                .requestMatchers(API.BASE_API + API.TRACKS + "/**").hasAnyAuthority(ROLE.MEMBER)
                .requestMatchers(API.BASE_API + API.MEMBERS + "/**").hasAnyAuthority(ROLE.SALE, ROLE.ADMIN)


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
