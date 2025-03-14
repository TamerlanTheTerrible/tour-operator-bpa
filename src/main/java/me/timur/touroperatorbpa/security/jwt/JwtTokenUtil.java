package me.timur.touroperatorbpa.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 07/05/22.
 */

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;

    public String getAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new java.util.Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new java.util.Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Set<SimpleGrantedAuthority> getAuthorities(String token) {
        var authorities = (List<Map<String, String>>) getClaims(token).get("authorities");

        if(authorities == null) {
            return null;
        }
        return  authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }
}
