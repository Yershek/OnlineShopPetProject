package com.example.amagazishi.security;

import com.example.amagazishi.exception.AuthorizeException;
import com.example.amagazishi.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHandler jwtHandler;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String autheader = parseJwt(request);


        if(Objects.nonNull(autheader) && jwtHandler.validateToken(autheader)) {
            String username = jwtHandler.extractUsernameFromToken(autheader);
            UserDetails userDetails = authService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken;
            authenticationToken = UsernamePasswordAuthenticationToken.authenticated(
                    userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");


        if(Objects.nonNull(authHeader) ){
            if(authHeader.contains("Basic ")) {
                throw new AuthorizeException("Ошибка авторизации ");
            }

            if(authHeader.startsWith("Bearer ") ) {
                return authHeader.substring(7);
            }
        }
        return null;
    }
}
