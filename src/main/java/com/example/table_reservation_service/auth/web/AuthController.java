package com.example.table_reservation_service.auth.web;

import com.example.table_reservation_service.auth.security.TokenProvider;
import com.example.table_reservation_service.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenProvider provider;
}
