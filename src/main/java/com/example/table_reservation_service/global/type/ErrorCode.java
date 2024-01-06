package com.example.table_reservation_service.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //common error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버에 오류가 발생했습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),

    //user error(소비자(일반), 매장 매니저 회원)
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "이메일에 해당되는 사용자가 없습니다."),
    MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "이메일에 해당되는 매니저가 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다"),
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),


    //store error
    ALREADY_EXIST_STORE(HttpStatus.BAD_REQUEST.value(), "이미 사용 중인 매장 이름입니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "해당 매장을 찾을 수 없습니다."),
    STORE_NOT_MATCH_MANAGER(HttpStatus.BAD_REQUEST.value(), "해당 매장의 매니저가 아닙니다."),

    //reservation error

    //review error



    // security error
    WRONG_TOKEN(HttpStatus.BAD_REQUEST.value(), "잘못된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰입니다."),

    TOKEN_TIME_OUT(HttpStatus.CONFLICT.value(), "만료된 Jwt 토큰입니다"),


    INVALID_ACCESS_TOKEN(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다."),

    JWT_TOKEN_WRONG_TYPE(HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 구성의 Jwt 토큰입니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED.value(), "로그인이 되지 않았습니다."),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED.value(), "잘못된 JWT 서명입니다."),
    ;


    private final int statusCode;
    private final String description;
}
