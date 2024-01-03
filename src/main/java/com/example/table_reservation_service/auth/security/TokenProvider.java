package com.example.table_reservation_service.auth.security;


import com.example.table_reservation_service.auth.service.AuthService;
import com.example.table_reservation_service.auth.type.MemberType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final AuthService authService;
    @Value("${spring.jwt.token-valid-time}")
    private long tokenValidTime;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public String generateToken(String email, MemberType memberType){
        SecretKey ket
    }

    private SecretKey getSecretKey(){
        return new SecretKeySpec(Base64.getDecoder().decode(this.secretKey),
                "HmacSHA256");
    }
}
