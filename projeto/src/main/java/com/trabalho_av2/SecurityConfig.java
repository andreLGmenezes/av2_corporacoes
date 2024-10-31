package com.trabalho_av2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/usuarios/cadastro", "/error").permitAll() // Permite acesso a endpoints específicos
                .anyRequest().authenticated()) // Exige autenticação para qualquer outro endpoint
            .httpBasic(withDefaults()) // Usa autenticação HTTP básica
            .formLogin(form -> form // Configura o login padrão do Spring Security
                .defaultSuccessUrl("/", true) // Redireciona para /home após login bem-sucedido
                .permitAll());

        return http.build();
    }
   
    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("andre")
                .password(passwordEncoder().encode("senha"))
                .roles("ADMIN")
                .build()
        );
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
