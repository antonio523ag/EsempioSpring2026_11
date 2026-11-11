package dev.antoniogrillo.primo.configuration;

import dev.antoniogrillo.primo.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfing {

    private final PersonaRepository repo;

    @Bean
    protected UserDetailsService userDetailsService(){
        return u->repo.findByEmail(u).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider(userDetailsService());
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }
}
