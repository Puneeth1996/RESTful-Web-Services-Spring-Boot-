package com.web.proj.WebService.security;

import com.web.proj.WebService.service.impl.UserServiceImpl;
import com.web.proj.WebService.shared.dto.UserDto;
import com.web.proj.WebService.ui.modal.response.ErrorMessages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
public class AuthenticationFilter extends OncePerRequestFilter throws Exception{

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try
    {
        String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        String token = null;
        String userEmail = null;
        if (authHeader != null && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = authHeader.substring(7);
            userEmail = jwtService.extractUsername(token);
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(userEmail);
            UserDto userDetailsForLoggedInUser = userService.getUser(userEmail);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                response.setHeader(SecurityConstants.HEADER_STRING, authHeader);
                response.setHeader("User-Id", request.getHeader("User-Id"));
            }
        }
        filterChain.doFilter(request, response);
    }
    catch (ex) {
        throw new Exception(ex);
    }

    }
}

