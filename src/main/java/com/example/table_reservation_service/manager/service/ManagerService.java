package com.example.table_reservation_service.manager.service;

import com.example.table_reservation_service.manager.dto.ManagerDto;
import com.example.table_reservation_service.manager.dto.RegisterManager;

public interface ManagerService {
    /**
     * 회원 가입
     */
     ManagerDto register(RegisterManager registerManager);
    /**
     * 유저 일치 확인
     */
     ManagerDto memberDetail(Long userId);
}
