package com.example.table_reservation_service.reservation.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrivalStatus {
    BEFORE_ARRIVAL("도착 전"),
    ARRIVED("도착 완료");
    private final String description;
}
