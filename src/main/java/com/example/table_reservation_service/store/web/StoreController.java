package com.example.table_reservation_service.store.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    // 매장 등록
    @PostMapping("/partner/create")
    @PreAuthorize("hasRole('PARTNER')")
    
    // 매장 수정

    // 매장 삭제

    // 매장 세부 정보

    // 매니저 - 매장 리스트
}
