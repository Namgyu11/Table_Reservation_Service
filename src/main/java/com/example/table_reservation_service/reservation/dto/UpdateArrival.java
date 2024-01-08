package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.reservation.type.ArrivalStatus;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;


/**
 * 도착 정보 업데이트
 */
public class UpdateArrival {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String customerName;
        private String phoneNumber;
        private LocalDateTime arrivalTime;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String customerName;
        private String storeName;

        private ReservationStatus reservationStatus;
        private ArrivalStatus arrivalStatus;

        public static Response fromDto(ReservationDto reservationDto) {
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .customerName(reservationDto.getCustomerName())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .arrivalStatus(reservationDto.getArrivalStatus())
                    .build();
        }

    }
}
