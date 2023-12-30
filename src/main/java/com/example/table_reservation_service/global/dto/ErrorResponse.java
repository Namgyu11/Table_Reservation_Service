package com.example.table_reservation_service.global.dto;

import com.example.table_reservation_service.global.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMassage;
}
