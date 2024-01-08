package com.example.table_reservation_service.review.web;

import com.example.table_reservation_service.review.dto.CreateReview;
import com.example.table_reservation_service.review.dto.ReviewDto;
import com.example.table_reservation_service.review.dto.UpdateReview;
import com.example.table_reservation_service.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * @param userId 리뷰를 작성할 고객 회원 ID
     * @param storeId 리뷰를 작성할 해당 매장 ID
     * @param reservationId 예약 ID
     * @param request 리뷰 작성을 할 정보
     * @return 작성된 정보
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReview.Response createReview(@RequestParam(name = "userid") Long userId,
                                              @RequestParam(name = "storeid") Long storeId,
                                              @RequestParam(name = "reservaitonid") Long reservationId,
                                              @RequestBody CreateReview.Request request) {

        return CreateReview.Response.fromDto(this.reviewService.createReview(
                userId, storeId, reservationId, request));
    }

    /**
     * 리뷰 수정
     * @param reviewId 해당 리뷰 ID
     * @param request 수정할 리뷰 정보
     * @return 수정된 리뷰 정보
     */    @PostMapping("/update/{reviewId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateReview.Response updateReview(@PathVariable Long reviewId,
                                              @RequestBody UpdateReview.Request request) {
        return UpdateReview.Response.fromDto(this.reviewService.updateReview(reviewId, request));
    }

    /**
     * 리뷰 삭제
     * @param reviewId 삭제할 리뷰 ID
     */
    @PostMapping("/delete/{reviewId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰가 삭제 되었습니다.");
    }
}
