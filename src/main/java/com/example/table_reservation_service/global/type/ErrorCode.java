package com.example.table_reservation_service.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //user error(소비자(일반), 매장 매니저 회원)
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"이메일에 해당되는 사용자가 없습니다."),
    MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"이메일에 해당되는 매니저가 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST.value(),"비밀번호가 일치하지 않습니다");





    private final int statusCode;
    private final String description;
}
