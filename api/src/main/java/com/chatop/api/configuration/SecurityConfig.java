package com.chatop.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
	private UserDetailsService jwtUserDetailsService;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
         return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable Cross-Site Request Forgery
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Spring Security will never create an HttpSession and it will never use it to obtain the Security Context     
                .and()
                .authorizeHttpRequests(auth -> 
                                auth.requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                                .anyRequest().authenticated()// user should be authenticated for any request in application
                )
                .httpBasic(); // Spring Security's HTTP Basic Authentication.
        
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}