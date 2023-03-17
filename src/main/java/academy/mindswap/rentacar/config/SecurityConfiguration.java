package academy.mindswap.rentacar.config;

import academy.mindswap.rentacar.aspects.UserExceptionHandler;
import academy.mindswap.rentacar.exceptions.EmailException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                //Admin has access to everything through localhost:8080/**
                .requestMatchers("/**").hasAuthority("ADMIN")

                //Employee has access to employee and client methods:
                .requestMatchers("/user/employee/**").hasAuthority("EMPLOYEE")
                .requestMatchers("/rentals/**").hasAuthority("EMPLOYEE")
                .requestMatchers("/cars/**").hasAuthority("EMPLOYEE")

                //client only has access to client methods:
                .requestMatchers("/rentals/client/**").hasAuthority("CLIENT")
                .requestMatchers("/cars/client/**").hasAuthority("CLIENT")

                //Every type of user has access to this paths:
                .requestMatchers("/user/client/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("You do not have permission to access this path");})
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return http.build();
    }
}

/*

/user/client
    /user/client/**.
  - view cars
  - view his rentals


/user/employee
    /user/employee/**.
    - can do everything besides /admin methods
    - create

/user/admin
    permitsAll
 */




