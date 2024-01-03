package com.example.table_reservation_service.manager.dto;

import com.example.table_reservation_service.auth.type.MemberType;
import com.example.table_reservation_service.manager.entity.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterManager {
    private String username;
    private MemberType memberType;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterManager fromDto(ManagerDto managerDto){
        return RegisterManager.builder()
                .username(managerDto.getUsername())
                .email(managerDto.getEmail())
                .password(managerDto.getPassword())
                .phoneNumber(managerDto.getPhoneNumber())
                .build();
    }
    public Manager toEntity(){
        return Manager.builder()
                .username(this.getUsername())
                .email(this.getEmail())
                .password(this.getPassword()) // 비밀번호는 서비스 계층에서 암호화 후 설정
                .phoneNumber(this.getPhoneNumber())
                .memberType(MemberType.PARTNER) // 회원 유형은 고정값 사용
                .build();
    }
}
