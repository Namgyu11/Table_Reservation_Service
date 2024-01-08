package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.reservation.type.ArrivalStatus;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private String customerName;
    private String customerPhoneNumber;
    private String storeName;

    private ReservationStatus reservationStatus;
    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public static ReservationDto fromEntity(Reservation reservation){
        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .customerName(reservation.getCustomer().getUsername())
                .customerPhoneNumber(reservation.getCustomer().getPhoneNumber())
                .storeName(reservation.getStore().getStoreName())
                .reservationStatus(reservation.getReservationStatus())
                .arrivalStatus(reservation.getArrivalStatus())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
