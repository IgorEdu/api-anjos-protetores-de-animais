package br.com.anjos_protetores_de_animais.api_controle_adocoes.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/api/pvt/**")
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Configurações Públicas e Auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pvt/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pvt/auth/signUp").permitAll()

                        // 2. REGRAS DE ADOÇÃO (CORRIGIDAS)
                        // Usuário pode ver os SEUS próprios pedidos (busca por ID do adotante)
                        .requestMatchers(HttpMethod.GET, "/api/pvt/adoptionRequests/adopter/**").hasAnyRole("USER", "ADMIN")
                        // Usuário pode criar um pedido
                        .requestMatchers(HttpMethod.POST, "/api/pvt/adoptionRequests").hasAnyRole("USER", "ADMIN") 
                        // Usuário pode cancelar (deletar) um pedido
                        .requestMatchers(HttpMethod.DELETE, "/api/pvt/adoptionRequests/**").hasAnyRole("USER", "ADMIN")
                        // Listar TODOS os pedidos do sistema continua sendo só para ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/pvt/adoptionRequests").hasRole("ADMIN")
                        // Outras operações de admin em pedidos (ex: aceitar/revogar que são PUT)
                        .requestMatchers("/api/pvt/adoptionRequests/**").hasRole("ADMIN")

                        // 3. ANIMAIS
                        .requestMatchers(HttpMethod.GET, "/api/pvt/animals").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pvt/animals/**").hasAnyRole("USER", "ADMIN")
                        // Criar/Editar/Apagar Animais -> Só Admin
                        .requestMatchers("/api/pvt/animals").hasRole("ADMIN")
                        .requestMatchers("/api/pvt/animals/**").hasRole("ADMIN")

                        // 4. ESPÉCIES
                        .requestMatchers(HttpMethod.GET, "/api/pvt/species").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/pvt/species/**").hasAnyRole("USER", "ADMIN")
                        // Criar/Editar/Apagar Espécies -> Só Admin
                        .requestMatchers("/api/pvt/species").hasRole("ADMIN") 
                        .requestMatchers("/api/pvt/species/**").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityPublicFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .securityMatcher("/api/pub/**")
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/pub/**").permitAll()
                .anyRequest().denyAll())
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}