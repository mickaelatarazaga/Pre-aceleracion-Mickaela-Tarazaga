package com.alkemy.DisneyAPIMickaelaTarazaga.auth.config;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.filter.JwtRequestFilter;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.UserDetailsCustomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsCustomService userDetailsCustomService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void setAttributes(UserDetailsCustomService userDetailsCustomService, @Lazy JwtRequestFilter jwtRequestFilter) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtRequestFilter = jwtRequestFilter;
    }
   
    //DEFINO MI PROPIO DETALLE DE USUARIOS
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService);
    }

    //MÉTDO QUE DEBERÍA ENCRIPTAR LA CONTRASEÑA
    //COMO NO ES UNA BUENA PRÁCTICA, LO PONE DEPRECADO
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
      
    //CONFIGURAR QUIEN ES EL MANEJADOR DE LA AUTENTIFICACIÓN
    //UTILIZO EL QUE PROVEE SPRING SECURITY
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    //CONFIGURAR COMO SE COMPORTA EL HTTP SECURITY
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*" ).permitAll()// CONFIGURO A QUE ENDPOINT LE APLICO SEGURIDAD 
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//POR DEFECTO SPRING SECURITY ES STATEFULL, POR LO QUE NO VUELVE A PEDIR AUTENTIFICACIÓN CADA VEZ QUE SE INGRESA A UN ENDPOINT

        //LE AGREGO UN FILTRO, (ANTES QUE CUAL)
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
}
