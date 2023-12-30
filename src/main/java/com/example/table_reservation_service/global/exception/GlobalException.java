package com.example.table_reservation_service.global.exception;

import com.example.table_reservation_service.global.type.ErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMassage;

    public GlobalException(ErrorCode errorCode) {

        super(errorCode.getDescription());
        this.errorCode= errorCode;
        this.errorMassage = errorCode.getDescription();
    }
}
