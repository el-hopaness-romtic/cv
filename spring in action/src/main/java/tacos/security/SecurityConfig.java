package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import tacos.data.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            tacos.User user = userRepo.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher.Builder(handlerMappingIntrospector)
                .servletPath("/h2-console")
                .pattern(HttpMethod.POST, "/**");

        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/design", "/orders").access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                        .anyRequest().permitAll()
                )
                .csrf().ignoringRequestMatchers(mvcRequestMatcher).and()
                .headers().frameOptions().sameOrigin().and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/design").and()
                .logout().logoutSuccessUrl("/").and()
                .build();
    }
}
