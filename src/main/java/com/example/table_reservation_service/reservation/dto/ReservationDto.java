package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.reservation.type.ArrivalStatus;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import com.example.table_reservation_service.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String username;
    private Store store;
    private ReservationStatus reservationStatus;
    private ArrivalStatus arrivalStatus;
    private LocalDate reservationDate;
    private LocalDate reservationTime;
}
