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

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // ATTRIBUTES
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

    /**
     * Filters every request before processing. Looks in the request header for an attribute called "Authorization" with a
     * String value starting with 'Bearer ' followed by a valid token. If it exists, the method takes that token a looks for
     * the username related to it. Then, looks for its UserDetails and validates the data setting the Authorization request
     * in the Security Context Holder
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Gets Authorization Token from Header
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // If there was any token in the Header and it starts with 'Bearer', it saves the token value and the username
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        // If username has value and there is no authentication in SecurityContextHolder, it validates the former and sets it in the latter
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Gets UserDetails using the custom service
            UserDetails userDetails = userDetailsCustomService.loadUserByUsername(username);

            // Validates the token and creates the Authentication Object
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());

                Authentication auth = authenticationManager.authenticate(authReq);

                // Set auth in context
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }
        filterChain.doFilter(request, response);
    }
}
