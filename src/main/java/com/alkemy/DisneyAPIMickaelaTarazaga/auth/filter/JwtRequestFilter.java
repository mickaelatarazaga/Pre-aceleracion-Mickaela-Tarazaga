package com.alkemy.DisneyAPIMickaelaTarazaga.auth.filter;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.JwtUtils;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;

//EL FILTRO SE EJECUTA CADA VEZ QUE LLEGUE UN REQUEST
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserDetailsCustomService userDetailsCustomService;
    private JwtUtils jwtUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAttributes(
            UserDetailsCustomService userDetailsCustomService,
            JwtUtils jwtUtil,
            AuthenticationManager authenticationManager) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
  
    //QUE DEBE HACER EL FILTRO CUANDO SE EJECUTA
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //SOLICITO EL HEADER POR NOMBRE "AUTHORIZATION"
        final String authorizationHeader = request.getHeader("Authorization");

        //CREO VARIABLES
        String username = null;
        String jwt = null;

        // Si había algún token en el encabezado y comienza con 'Bearer', guarda el valor del token y el nombre de usuario
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);//Saca los primeros 7 caracteres (Bearer )para obtener el valor real
            username = jwtUtil.extractUsername(jwt);
        }

        // Si el nombre de usuario tiene valor y no hay autenticación en SecurityContextHolder, valida el primero y lo establece en el segundo
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Obtiene UserDetails usando el servicio personalizado
            UserDetails userDetails = userDetailsCustomService.loadUserByUsername(username);
            // Valida el token y crea el objeto de autenticación
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());

                Authentication auth = authenticationManager.authenticate(authReq);

                // Establecer autenticación en contexto
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }
        filterChain.doFilter(request, response);
    }
}
