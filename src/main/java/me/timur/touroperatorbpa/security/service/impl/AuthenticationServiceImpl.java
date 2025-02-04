//package me.timur.touroperatorbpa.security.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.timur.touroperatorbpa.exception.AuthenticationException;
//import me.timur.touroperatorbpa.model.enums.ResponseCode;
//import me.timur.touroperatorbpa.security.jwt.JwtUtil;
//import me.timur.touroperatorbpa.security.model.TokenDTO;
//import me.timur.touroperatorbpa.security.service.AuthenticationService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Temurbek Ismoilov on 06/08/23.
// */
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class AuthenticationServiceImpl implements AuthenticationService {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//
//    @Override
//    public TokenDTO login(String username, String password) {
//        log.info("Logging in user with username: {}", username);
//
//        Authentication authentication = null;
//        try {
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (Exception e) {
//            throw new AuthenticationException(e.getMessage());
//        }
//
//        String accessToken = jwtUtil.getAccessToken(username, authentication.getAuthorities());
//        String refreshToken = jwtUtil.getRefreshToken(username);
//        return new TokenDTO(accessToken, refreshToken);
//    }
//}
