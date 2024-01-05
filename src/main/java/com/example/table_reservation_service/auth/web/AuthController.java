package com.example.table_reservation_service.auth.web;

import com.example.table_reservation_service.auth.dto.LoginInput;
import com.example.table_reservation_service.auth.security.TokenProvider;
import com.example.table_reservation_service.auth.service.AuthService;
import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.manager.entity.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;


    /**
     * 일반 회원(고객) 로그인
     * @param request 로그인(ID,PWD) 요청
     * @return DB에 해당된 ID,PWD 매칭하여 일반 회원 로그인 완료 토큰
     */

    @PostMapping("/customer")
    public ResponseEntity<?> customerLogin(@RequestBody @Valid LoginInput request){
        Customer customer = this.authService.authenticateCustomer(request);
        return ResponseEntity.ok(
                this.tokenProvider.generateToken(customer.getEmail(), customer.getMemberType())
        );
    }


    /**
     * 매장 매니저 로그인
     * @param request 로그인(ID,PWD) 요청
     * @return DB에 해당된 ID,PWD 매칭하여 매장 매니저 로그인 완료 토큰
     */
    @PostMapping("/manager")
    public ResponseEntity<?> managerLogin(@RequestBody @Valid LoginInput request){
        Manager manager = this.authService.authenticateManager(request);
        return ResponseEntity.ok(
                this.tokenProvider.generateToken(manager.getEmail(), manager.getMemberType())
        );
    }


}
