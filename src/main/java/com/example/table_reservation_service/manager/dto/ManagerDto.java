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
public class ManagerDto {

    private Long id;
    private String username;
    private MemberType memberType;
    private String email;
    private String password;
    private String phoneNumber;

    public static ManagerDto fromEntity(Manager manager){
        return ManagerDto.builder()
                .id(manager.getId())
                .username(manager.getUsername())
                .memberType(manager.getMemberType())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }


}
