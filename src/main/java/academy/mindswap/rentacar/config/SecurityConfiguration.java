package academy.mindswap.rentacar.config;

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

                //Every type of user has access to this paths:
                .requestMatchers("/user/client/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()

                //Admin has access to everything through localhost:8080/**
                .requestMatchers("/**").hasAuthority("ADMIN")

                //Employee has access to employee and client methods:
                .requestMatchers("/user/employee/**").hasAuthority("EMPLOYEE")
                .requestMatchers("/rentals/**").hasAuthority("EMPLOYEE")
                .requestMatchers("/cars/**").hasAuthority("EMPLOYEE")

                //client only has access to client methods:
                .requestMatchers("/rentals/client/**").hasAuthority("CLIENT")
                .requestMatchers("/cars/client/**").hasAuthority("CLIENT")


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

*/


 /* ---------------- CURRENT IMPLEMENTATION ----------------
         /client/**
                   /info/ -  view my personal details
                   /available-movies - view available movies (movies_id, store_id, imdb_rating,...)
                   /rental/** - view the list of my rentals (rentals_id, movie name, start date, invoice_id)
                             /id/ view specific rental (client_name, rental_id, movie name, start date, deliver date, price, invoice_id)
                   /invoice/** - view the list of my invoices (invoice_id, total_price)
                              /id/** - view specific invoice
                                    /details - view details in json or in html page
                                    /qrcode - gets a QRCODE
                                    /email - gets invoice to email


          /worker/**
                    /info/ -  view my personal details
                    /info/id/** - view client details
                               /rental/** - view the list of specific client rentals (rentals_id, movie name, start date, invoice_id)
                                         /id/ view specific rental (client_name, rental_id, movie name, start date, deliver date, price, invoice_id)
                                         /id/deliver - changes the boolean hasDelivered when the delivers the movies
                               /invoice/** - view the list of specific client invoices (invoice_id, total_price)
                                        /id/**
                                              /details - view details in json or in html page
                                              /qrcode - get a QRCODE
                                              /email - sends invoice to client email

                    /movies/** - check all rented and available movies
                              /available/** - view available movies (movies_id, store_id)
                                           /store/ - view available movies where the worker works
                              /rented/** - view all rented movies
                                        /store/ - view rented movies where the worker works


                    /client-list - view the list of clients (client_id, client_name, ...)
                    /rental-list - view the list of all rentals (client_id, client_name, rental_id)
                    /create-order - create rental order
                    /create-invoice - create rental invoice with various orders



         /manager/**
                    /info/ -  view my personal details
                    /insert-movie
                    /update-movie
                    /delete/movie_id
                    /register-worker
                    /remove-employee/employee_id
        -> has access to all employee methods.



         /admin/**
                 /create-shop
                 /create-manager
                 /update-shop
                 /update-admin
            -> has access to all methods
  */



 /*  ---------------- 2 IMPLEMENTATION ----------------
         /client/**
                   /id/ -  view my personal details
                   /update/
                   /delete/
                   /all

         /movies/**
                   /id
                   /add
                   /update
                   /delete
                   /all
                   /available

         /rental/**
                   /id
                   /add
                   /update
                   /delete
                   /all

         /invoice/**
                    /id/** - view specific invoice
                          /details - view details in json or in html page
                          /qrcode - gets a QRCODE
                          /email - gets invoice to email
                    /add
                    /update
                    /delete
                    /all

          /store/**
                   /create
                   /update/
                   /delete/
                   /id
                   /all

          /worker/**
                    /id/ -  view my personal details
                    /update/
                    /delete/
                    /id
                    /all

          /manager/**
                     /id/ -  view my personal details
                     /update/
                     /delete/
                     /add
                     /all

          /admin/**
                   /id/ -  view my personal details
                   /update/
                   /delete/
                   /add
                   /all
  */







