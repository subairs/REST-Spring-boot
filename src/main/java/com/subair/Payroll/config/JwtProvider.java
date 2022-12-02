package com.subair.Payroll.config;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
    
    private Key key;

    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication authentication){
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                                                            .collect(Collectors.joining(","));
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("Authorities_key", authorities)
                    .signWith(key)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+30*1000))
                    .compact();
    }

    public boolean validateToken(String jwt) { 
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    public String getEmailFromJWT(String jwt) {
        Claims claims = Jwts.parser()
                            .setSigningKey(key)
                            .parseClaimsJws(jwt)
                            .getBody();
        return claims.getSubject();
    }
}