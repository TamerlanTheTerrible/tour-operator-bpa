package me.timur.touroperatorbpa.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.domain.entity.User;
import me.timur.touroperatorbpa.domain.repository.UserRepository;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by Temurbek Ismoilov on 12/03/25.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserContextFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new ClientException(ResponseCode.RESOURCE_NOT_FOUND, "User %s not found", authentication.getName()));

            UserContext.setUser(user);
        } else {
            log.error(authentication.getName() + " not authenticated");
            throw new ClientException(ResponseCode.AUTHENTICATION_ERROR, "User not authenticated");
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();  // Ensure cleanup after request
        }
    }
}
