package me.timur.touroperatorbpa.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by Temurbek Ismoilov on 06/08/23.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtVerifierFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authorizationHeader.replace("Bearer ", "");

        try {
            // fetch username and authorities from token
            var username = jwtUtil.getUsername(token);
            var grantedAuthorities = jwtUtil.getAuthorities(token);
            // add authentication to the security context
            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities));
            SecurityContextHolder.setContext(context);
            // go to the next filter
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            log.error(String.format("Token %s cannot be trusted", token), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("UNAUTHORIZED");
        }
    }
}
