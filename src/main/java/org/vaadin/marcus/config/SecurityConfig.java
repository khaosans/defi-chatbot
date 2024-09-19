package org.vaadin.marcus.config;

import org.springframework.context.annotation.Bean;

import java.beans.Customizer;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login", "/logout").permitAll() // Allow access to login and logout
				.anyRequest().authenticated()
			)
			.httpBasic() // Corrected the method call
			.and() // Added 'and()' to chain the configuration correctly
			.formLogin(); // Enable form login
        return http.build();
	} // Added closing brace to complete the method body

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
			.username("user")
			.password("{noop}password") // Use {noop} to indicate no password encoding
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

}