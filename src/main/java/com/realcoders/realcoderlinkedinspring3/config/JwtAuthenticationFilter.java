package com.realcoders.realcoderlinkedinspring3.config;

import com.realcoders.realcoderlinkedinspring3.UserRepository.UserRepository;
import com.realcoders.realcoderlinkedinspring3.exceptions.InvalidJwtTokenException;
import com.realcoders.realcoderlinkedinspring3.jwtService.JwtService;
import com.realcoders.realcoderlinkedinspring3.user.User;
import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {


    private  final JwtService jwtService;

    private final UserRepository userRepository;
    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            @NonNull ServletRequest servletRequest,
            @NonNull ServletResponse servletResponse,
            @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = new HttpServletRequestWrapper((HttpServletRequest)servletRequest);
        HttpServletResponse response = new HttpServletResponseWrapper((HttpServletResponse)servletResponse);
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write("The provided JWT is invalid or has expired.".getBytes());
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        if (username != null && UserContext.getUser() == null) {
            if (this.userRepository.findByUsername(username).isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getOutputStream().write("The provided JWT is invalid or has expired.".getBytes());
                return;
            }
            User user = this.userRepository.findByUsername(username).orElseThrow(() ->
                     new InvalidJwtTokenException("The provided JWT is invalid or has expired."));
            if (!jwtService.isTokenValid(jwt, user)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getOutputStream().write("The provided JWT is invalid or has expired.".getBytes());
                return;
            }
            UserContext.setUser(user);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
