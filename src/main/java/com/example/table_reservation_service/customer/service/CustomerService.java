package com.example.table_reservation_service.customer.service;

import com.example.table_reservation_service.customer.dto.CustomerDto;
import com.example.table_reservation_service.customer.dto.RegisterCustomer;

public interface CustomerService {

    /**
     * 회원 가입
     */
    CustomerDto register(RegisterCustomer registerCustomer);

    /**
     * 유저 일치 확인
     */
    CustomerDto memberDetail(Long userId);
}
