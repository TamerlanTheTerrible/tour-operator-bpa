package me.timur.touroperatorbpa.security.service.impl;

import lombok.RequiredArgsConstructor;
import me.timur.touroperatorbpa.security.jwt.JwtUtil;
import me.timur.touroperatorbpa.security.model.TokenDTO;
import me.timur.touroperatorbpa.security.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 06/08/23.
 */

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public TokenDTO login(String email, String password) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String accessToken = jwtUtil.getAccessToken(email, authentication.getAuthorities());
        String refreshToken = jwtUtil.getRefreshToken(email);
        return new TokenDTO(accessToken, refreshToken);
    }
}
