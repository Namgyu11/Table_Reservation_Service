package com.example.table_reservation_service.customer.service;

import com.example.table_reservation_service.customer.dto.CustomerDto;
import com.example.table_reservation_service.customer.dto.RegisterCustomer;
import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.customer.repository.CustomerRepository;
import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.global.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CustomerDto register(RegisterCustomer registerCustomer) {
        boolean exists = this.customerRepository.existsByEmail(registerCustomer.getEmail());
        if (exists) {
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_USER);
        }
        registerCustomer.setPassword(this.passwordEncoder.encode(registerCustomer.getPassword()));
        Customer saveInfo = this.customerRepository.save(registerCustomer.toEntity());

        return CustomerDto.fromEntity(saveInfo);
    }

    /**
     * 등록된 ID로 회원 정보 확인
     *
     * @param userId 입력 받은 ID
     * @return 해당된 ID에 관한 유저 정보
     */
    @Override
    public CustomerDto memberDetail(Long userId) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        return CustomerDto.fromEntity(customer);
    }
}
