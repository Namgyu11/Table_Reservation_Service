package com.example.table_reservation_service.reservation.service;

import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.customer.repository.CustomerRepository;
import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.reservation.dto.CreateReservation;
import com.example.table_reservation_service.reservation.dto.ReservationDto;
import com.example.table_reservation_service.reservation.dto.UpdateArrival;
import com.example.table_reservation_service.reservation.dto.UpdateReservation;
import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.reservation.repository.ReservationRepository;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import com.example.table_reservation_service.store.entity.Store;
import com.example.table_reservation_service.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.table_reservation_service.global.type.ErrorCode.*;
import static com.example.table_reservation_service.reservation.type.ArrivalStatus.ARRIVED;
import static com.example.table_reservation_service.reservation.type.ArrivalStatus.BEFORE_ARRIVAL;
import static com.example.table_reservation_service.reservation.type.ReservationStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public ReservationDto createReservation(CreateReservation.Request request) {
        log.info("==== 예약 등록: {} ====", request.toString());

        Store store = this.storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GlobalException(STORE_NOT_FOUND));

        Customer customer = this.customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));

        LocalDateTime reserveTime = LocalDateTime.of(
                request.getReservationDate(), request.getReservationTime());

        boolean exists = this.reservationRepository.existsReservationTime(reserveTime);

        if (exists) {
            log.info(ALREADY_RESERVED.getDescription());
            throw new GlobalException(ALREADY_RESERVED);
        }

        Reservation reservation = this.reservationRepository.save(Reservation.builder()
                .customer(customer)
                .store(store)
                .reservationStatus(STANDBY)
                .arrivalStatus(BEFORE_ARRIVAL)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime())
                .build());

        log.info("***** 예약 등록 완료 *****");
        return ReservationDto.fromEntity(reservation);
    }

    // 예약 승인 및 승인 취소
    @Override
    @Transactional
    public ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request) {
        log.info("==== 예약 승인 및 승인취소 ====");

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(RESERVATION_NOT_FOUND));

        ReservationStatus status = reservation.getReservationStatus();

        // 상태 코드가 일치한다면 에러 발생
        if (status.equals(request.getReservationStatus())) {
            log.info(RESERVATION_STATUS_CODE_ERROR.getDescription());
            throw new GlobalException(RESERVATION_STATUS_CODE_ERROR);
        }

        reservation.setReservationStatus(request.getReservationStatus());

        return ReservationDto.fromEntity(this.reservationRepository.save(reservation));
    }

    // 예약 리스트
    @Override
    public List<Reservation> searchReservation(Long managerId) {
        log.info("==== 예약 리스트 ====");
        List<Reservation> reservationList =
                this.reservationRepository.findAllByManagerReservation(managerId);

        if (reservationList.isEmpty()) {
            log.info(RESERVATION_NOT_FOUND.getDescription());
            throw new GlobalException(RESERVATION_NOT_FOUND);
        }

        return reservationList;
    }

    // 매장 도착 확인 여부 변경
    @Override
    @Transactional
    public ReservationDto updateArrival(Long reservationId, UpdateArrival.Request request) {
        log.info("==== 예약자 매장 도착 여부 변경 ====");
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(RESERVATION_NOT_FOUND));

        validationReservation(reservation, request.getArrivalTime().toLocalTime());

        reservation.setArrivalStatus(ARRIVED);
        reservation.setReservationStatus(USE_COMPLETED);
        log.info("==== 예약자 매장 도착 여부 변경 완료====");

        return ReservationDto.fromEntity(this.reservationRepository.save(reservation));
    }

    // 예약 취소
    @Override
    @Transactional
    public ReservationDto cancelReservation(Long reservationId) {
        log.info("==== 예약 취소 시작 ====");

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(RESERVATION_NOT_FOUND));

        reservation.setReservationStatus(CANCELED);

        log.info("==== 예약 취소 성공 ====");

        return ReservationDto.fromEntity(this.reservationRepository.save(reservation));
    }

    /**
     * 예약 유효성 검사
     * 1. 예약 상태가 '승인상태'인지 확인
     * 2. 예약 시간이 지났을 경우
     * 3. 예약  10분 이하로 남았을 경우 키오스크 통해 확인 가능
     *
     * @param reservation 예약 정보
     * @param arrivalTime 도착 시간
     */
    private void validationReservation(Reservation reservation, LocalTime arrivalTime) {
        if (!reservation.getReservationStatus().equals(ReservationStatus.APPROVAL)) {
            throw new GlobalException(RESERVATION_STATUS_CODE_ERROR);
        } else if (arrivalTime.isAfter(reservation.getReservationTime())) {
            throw new GlobalException(RESERVATION_TIME_EXCEEDED);
        } else if (arrivalTime.isBefore(reservation.getReservationTime()
                .minusMinutes(10L))) {
            throw new GlobalException(CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME);
        }
    }
}
