package com.example.table_reservation_service.customer.dto;

import com.example.table_reservation_service.auth.type.MemberType;
import com.example.table_reservation_service.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterCustomer {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterCustomer fromDto(CustomerDto customerDto){
        return RegisterCustomer.builder()
                .username(customerDto.getUsername())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }
    public Customer toEntity() {
        return Customer.builder()
                .username(this.getUsername())
                .email(this.getEmail())
                .password(this.getPassword()) // 비밀번호는 서비스 계층에서 암호화 후 설정
                .phoneNumber(this.getPhoneNumber())
                .memberType(MemberType.CUSTOMER) // 회원 유형은 고정값 사용
                .build();
    }

}
