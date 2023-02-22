package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

import static java.util.Collections.singletonList;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(Arrays.asList(
                new User(
                        "buzz", encoder.encode("password"), singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                ),
                new User(
                        "woody", encoder.encode("password"), singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                )
        ));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher.Builder(handlerMappingIntrospector)
                .servletPath("/h2-console")
                .pattern(HttpMethod.POST, "/**");

        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/design", "/orders").access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                        .anyRequest().access(new WebExpressionAuthorizationManager("permitAll()"))
                )
                .csrf().ignoringRequestMatchers(mvcRequestMatcher).and()
                .headers().frameOptions().sameOrigin().and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/design").and()
                .logout().logoutSuccessUrl("/").and()
                .build();
    }
}
