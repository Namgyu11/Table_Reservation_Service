package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.reservation.type.ReservationStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateReservation {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class Request{
        private ReservationStatus reservationStatus;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class Response{
        private Long reservationId;
        private String customerName;
        private String storeName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response fromDto(ReservationDto reservationDto){
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .customerName(reservationDto.getCustomerName())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }

    }
}
