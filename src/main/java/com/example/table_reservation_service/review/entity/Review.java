package com.example.table_reservation_service.review.entity;

import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.global.entity.BaseEntity;
import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review extends BaseEntity {
    /**
     * 리뷰 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 매장 ID
     */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * 일반 회원 ID
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 예약 ID
     */
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    /**
     * 리뷰 내용
     */
    @Column(length = 300)
    private String content;

    /**
     * 리뷰 별점
     */
    @Column(precision = 2, scale = 1)
    @Digits(integer = 1, fraction = 1)
    private double rating;


}
