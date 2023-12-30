package com.example.table_reservation_service.auth.service;

import com.example.table_reservation_service.auth.type.MemberType;
import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.customer.repository.CustomerRepository;
import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.global.type.ErrorCode;
import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.manager.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원 이메일을 이용하여 DB의 이메일과 일치하는 회원을 찾음
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (this.customerRepository.existsByEmail(email)) {

            Customer customer = checkUserEmail(email);

            return createUserDetails(customer.getEmail(), customer.getPassword(),
                    MemberType.CUSTOMER);

        } else if (this.managerRepository.existsByEmail(email)) {

            Manager manager = checkManager(email);

            return createUserDetails(manager.getEmail(), manager.getPassword(),
                    MemberType.PARTNER);
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    private UserDetails createUserDetails(String username, String password
            , MemberType memberType) {
        return User.withUsername(username)
                .password(this.passwordEncoder.encode(password))
                .roles(String.valueOf(memberType))
                .build();
    }

    //일반 회원 등록 이메일 확인
    private Customer checkUserEmail(String email) {
        return this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));
    }

    //매장 매니저 회원 등록 이메일 확인
    private Manager checkManager(String email) {
        return this.managerRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(ErrorCode.MANAGER_NOT_FOUND));
    }
}
