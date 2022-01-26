package com.alkemy.DisneyAPIMickaelaTarazaga.auth.config;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.filter.JwtRequestFilter;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // ATTRIBUTES
    private UserDetailsCustomService userDetailsCustomService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void setAttributes(UserDetailsCustomService userDetailsCustomService, @Lazy JwtRequestFilter jwtRequestFilter) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Configure method to override the service used for UserDetails
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService);
    }

    /**
     * No cripting password yet for being an educational project
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configure method to override HttpSecurity behaviour.
     * Csrf disabled, any requests pointing to "/auth/*" lacks of security, and requests sessions are stateless
     * Adds JwtRequestFilter as 'once per request' filter
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
