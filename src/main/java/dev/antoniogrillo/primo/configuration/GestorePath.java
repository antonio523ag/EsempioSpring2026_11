package dev.antoniogrillo.primo.configuration;

import dev.antoniogrillo.primo.entity.Ruolo;
import dev.antoniogrillo.primo.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class GestorePath {

    private final AuthenticationProvider authenticationProvider;
    private final JWTFilter jwtFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http){
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers("/persone/login").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(HttpMethod.POST, "/persone/registrati").permitAll()
                        .requestMatchers("/cliente/getProfilo").hasRole(Ruolo.CLIENTE.name())
                        .requestMatchers("/cliente/**").hasRole(Ruolo.CLIENTE.name())
                        .requestMatchers("/getAbitazione").hasAnyRole(Ruolo.CLIENTE.name(),Ruolo.VENDITORE.name())
                        .anyRequest().permitAll()
                ).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
}
