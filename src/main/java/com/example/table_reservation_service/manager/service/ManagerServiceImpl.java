package com.example.table_reservation_service.manager.service;

import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.global.type.ErrorCode;
import com.example.table_reservation_service.manager.dto.ManagerDto;
import com.example.table_reservation_service.manager.dto.RegisterManager;
import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.manager.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ManagerDto register(RegisterManager registerManager) {
        boolean exists = this.managerRepository.existsByEmail(registerManager.getEmail());
        if (exists) {
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        registerManager.setPassword(this.passwordEncoder.encode(
                registerManager.getPassword()));

        Manager saveMangerInfo = this.managerRepository.save(registerManager.toEntity());
        return ManagerDto.fromEntity(saveMangerInfo);
    }


    /**
     * 등록된 ID로 회원 정보 확인
     *
     * @param userId 입력 받은 ID
     * @return 해당된 ID에 관한 유저 정보
     */
    @Override
    public ManagerDto memberDetail(Long userId) {
        Manager manager = this.managerRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.MANAGER_NOT_FOUND));

        return ManagerDto.fromEntity(manager);
    }
}
