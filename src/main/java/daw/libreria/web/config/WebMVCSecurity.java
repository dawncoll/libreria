package daw.libreria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebMVCSecurity {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user1")
            .password("{noop}user1Pass")
            .authorities("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers("/resources/**");
//    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    	return http
    			.csrf(csrf -> csrf.disable())
    			.authorizeHttpRequests(auth -> auth.requestMatchers("/images/**", "/css/**", "/js/**", "/webjars/**").permitAll())
    			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
    			.formLogin(withDefaults())
    			.build();
    }

}