package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long customerId;

        @NotNull
        private Long storeId;

        private LocalDate reservationDate;
        private LocalTime reservationTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String customerName;
        private String customerPhoneNumber;
        private String storeName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response fromDto(ReservationDto reservationDto) {
            return Response.builder()
                    .customerName(reservationDto.getCustomerName())
                    .customerPhoneNumber(reservationDto.getCustomerPhoneNumber())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }


    }
}
