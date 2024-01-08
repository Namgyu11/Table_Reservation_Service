package com.example.table_reservation_service.reservation.dto;

import com.example.table_reservation_service.reservation.entity.Reservation;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchReservation {

    public List<ReservationDto> reservationList;

    public static SearchReservation fromEntityList(List<Reservation> reservationList) {
        return new SearchReservation(reservationList.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList()));
    }
}
