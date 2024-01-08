package com.example.table_reservation_service.reservation.service;

import com.example.table_reservation_service.reservation.dto.CreateReservation;
import com.example.table_reservation_service.reservation.dto.ReservationDto;
import com.example.table_reservation_service.reservation.dto.UpdateArrival;
import com.example.table_reservation_service.reservation.dto.UpdateReservation;
import com.example.table_reservation_service.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {


    /**
     * 매장 예약
     * @param request 예약 등록할 정보
     * @return 예약된 정보
     */
    ReservationDto createReservation(CreateReservation.Request request);



    /**
     * 예약 승인 및 승인 취소
     * @param Id 등록된 예약 고유 ID
     * @param request 예약 승인 및 승인 취소 수정
     * @return 수정된 예약 정보
     */
    ReservationDto updateReservation(Long Id,UpdateReservation.Request request);

    // 예약 리스트
    List<Reservation> searchReservation(Long Id);

    // 매장 도착확인 여부 변경
    ReservationDto updateArrival(Long reservationId, UpdateArrival.Request request);

    // 예약 취소
    ReservationDto cancelReservation(Long reservationId);
}
