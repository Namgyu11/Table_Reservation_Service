package com.example.table_reservation_service.manager.web;

import com.example.table_reservation_service.manager.dto.RegisterManager;
import com.example.table_reservation_service.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    /**
     * 회원 가입
     * @param  request 회원가입 할 회원 정보
     * @return 회원가입 된 회원 정보
     */
    @PostMapping("/register/manager")
    public ResponseEntity<?> register(@RequestBody RegisterManager request){
        return ResponseEntity.ok().body(
                request.fromDto(this.managerService.register(request)));
    }

    /**
     * 매장 매니저 정보 조회
     * @param id 사용자 아이디
     * @return 사용자 아이디에 해당하는 정보
     */
    @GetMapping("/partner/info")
    @PreAuthorize("hasAnyRole('PARTNER')")
    public ResponseEntity<?> getManagerInfo(@RequestParam("id") Long id){
        return ResponseEntity.ok(this.managerService.memberDetail(id));
    }
}
