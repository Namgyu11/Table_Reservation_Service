package com.example.table_reservation_service.reservation.web;

import com.example.table_reservation_service.reservation.dto.CreateReservation;
import com.example.table_reservation_service.reservation.dto.SearchReservation;
import com.example.table_reservation_service.reservation.dto.UpdateArrival;
import com.example.table_reservation_service.reservation.dto.UpdateReservation;
import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 매장 예약 진행
     * @param request 예약할 정보
     * @return 예약된 정보
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request){

        return CreateReservation.Response
                .fromDto(this.reservationService.createReservation(request));
    }


    // 예약 승인 및 승인 취소

    /**
     * 예약 승인 및 승인 취소(거절)
     * @param id 해당 예약 고유 ID
     * @param request 수정할 예약 상태
     * @return 수정된 예약 정보(상태 포함)
     */
    @PutMapping("/partner/approval/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateReservation.Request request){

        return UpdateReservation.Response
                .fromDto(this.reservationService.updateReservation(id, request));
    }

    /**
     * 예약 리스트
     * @param id 매장 매니저 ID
     * @return 해당 매장에 예약 리스트
     */
    @GetMapping("/partner/reservation-list/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public SearchReservation getReservationList(@PathVariable Long id){
        List<Reservation> reservationList = this.reservationService.searchReservation(id);

        return SearchReservation.fromEntityList(reservationList);
    }


    /**
     * 매장 도착 확인 여부
     * @param id 해당 예약 ID
     * @param request 확인 상태 코드
     * @return 변경된 도착 여부 포함 예약 정보
     */
    @GetMapping("/kiosk/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateArrival.Response updateArrival(
            @PathVariable Long id,
            @RequestBody UpdateArrival.Request request
    ){

        return UpdateArrival.Response
                .fromDto(this.reservationService.updateArrival(id,request));
    }

    /**
     * 예약 취소
     * @param reservationId 해당 예약 ID
     * @return 취소된 예약 정보
     */
    @PutMapping("/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> cancelReservation(
            @RequestParam(name = "reservationid") Long reservationId){

        return ResponseEntity.ok(this.reservationService.cancelReservation(reservationId));
    }

}
