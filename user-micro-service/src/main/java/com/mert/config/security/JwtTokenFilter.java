package com.mert.config.security;

import com.mert.exception.ErrorType;
import com.mert.exception.UserProfileException;
import com.mert.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        if(authHeader!=null&&authHeader.startsWith("Bearer ")&& SecurityContextHolder.getContext().getAuthentication()==null){
            String token = authHeader.substring(7);
            Optional<String> userRole = jwtTokenManager.getRoleFromToken(token);
            if(jwtTokenManager.validateToken(token).isPresent()){
                UserDetails userDetails = jwtUserDetails.getUserByUserRole(userRole.get());

                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
