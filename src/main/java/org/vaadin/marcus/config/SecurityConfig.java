package org.vaadin.marcus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/login", "/logout").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(withDefaults())
			.formLogin(withDefaults());
        return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
			.username("user")
			.password("{noop}password")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
}