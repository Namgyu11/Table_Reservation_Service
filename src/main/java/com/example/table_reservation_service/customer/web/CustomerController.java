package com.example.table_reservation_service.customer.web;

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

    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request) {
        return ResponseEntity.ok().body(
                request.from(this.customerService.register(request)));
    }
    @GetMapping("/customer/info")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> getCustomerInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(this.customerService.memberDetail(id));
    }
}
