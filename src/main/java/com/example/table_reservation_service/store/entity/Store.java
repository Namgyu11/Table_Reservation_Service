package com.example.table_reservation_service.store.entity;

import com.example.table_reservation_service.global.entity.BaseEntity;
import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Store extends BaseEntity {

    /**
     * 매장 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    /**
     * 매장 매니저 ID
     */
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    /**
     *  매장 이름
     */
    @NotBlank
    private String storeName;

    /**
     * 매장 위치
     */
    @NotBlank
    private String location;

    /**
     * 매장 위치
     */
    @NotBlank
    private String phoneNumber;

    /**
     * Reservation table join
     */
    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>(); // 일 대 다 관계에서 List 활용

    /**
     * review table join
     */
    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>(); // 일 대 다 관계에서 List 활용


}
