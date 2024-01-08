package com.example.table_reservation_service.reservation.repository;

import com.example.table_reservation_service.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * 해당 시간 매장 예약 가능 상태
     * @param reservationTime 예약하고 싶은 시간
     * @return 해당 시간 예약 여부
     */
    @Query("select exists " +
            "(select 1 from Reservation r " +
            "where r.reservationDate = :#{#reservationTime.toLocalDate()} " +
            "and r.reservationTime = :#{#reservationTime.toLocalTime()})")
    boolean existsReservationTime(@Param("reservation")LocalDateTime reservationTime);

    /**
     *  매장 예약 리스트 확인
     * @param id 매장 매니저 ID
     * @return 예약 리스트
     */
    @Query("select r from Reservation r " +
            "where r.store.manager.id = :id " +
            "order by r.reservationDate")
    List<Reservation> findAllByManagerReservation(@Param("id") Long id);


}
