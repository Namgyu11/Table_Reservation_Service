package com.example.table_reservation_service.reservation.entity;

import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.global.entity.BaseEntity;
import com.example.table_reservation_service.reservation.type.ArrivalStatus;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import com.example.table_reservation_service.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reservation extends BaseEntity {

    /**
     * 예약 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 일반 회원 ID
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 매장 ID
     */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * 승인 여부 확인
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    /**
     * 도착 여부 확인
     */
    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus;

    /**
     * 매장 예약 날짜
     */
    private LocalDate reservationDate;

    /**
     * 매장 예약 시간
     */
    private LocalTime reservationTime;


}
