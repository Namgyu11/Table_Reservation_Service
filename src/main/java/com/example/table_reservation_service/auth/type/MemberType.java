package com.example.table_reservation_service.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    /**
     * 일반 사용자(고객)
     */
    CUSTOMER("ROLE_CUSTOMER"),
    /**
     * 점장(매장 매니저)
     */
    PARTNER("ROLE_PARTNER");

    private final String description;

}
