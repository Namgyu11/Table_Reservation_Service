package com.example.table_reservation_service.customer.web;

import com.example.table_reservation_service.customer.dto.RegisterCustomer;
import com.example.table_reservation_service.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    /**
     * 회원 가입
     * @param  request 회원가입 할 회원 정보
     * @return 회원가입 된 회원 정보
     */
    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request){
        return ResponseEntity.ok().body(
                request.fromDto(this.customerService.register(request)));
    }


    /**
     * 모든 회원(일반, 매장 매니저) 정보 조회
     * @param id 사용자 아이디
     * @return 사용자 아이디에 해당하는 정보
     */
    @GetMapping("/customer/info")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> getCustomerInfo(@RequestParam("id") Long id){
        return ResponseEntity.ok(this.customerService.memberDetail(id));
    }

}
