package com.example.table_reservation_service.review.service;

import com.example.table_reservation_service.review.dto.CreateReview;
import com.example.table_reservation_service.review.dto.ReviewDto;
import com.example.table_reservation_service.review.dto.UpdateReview;

public interface ReviewService {

    /**
     * 리뷰 작성
     * @param userId 리뷰를 작성할 고객 회원 ID
     * @param storeId 리뷰를 작성할 해당 매장 ID
     * @param reservationId 예약 ID
     * @param request 리뷰 작성을 할 정보
     * @return 작성된 정보
     */
    ReviewDto createReview(Long userId, Long storeId, Long reservationId,
                                  CreateReview.Request request);

    /**
     * 리뷰 수정
     * @param reviewId 해당 리뷰 ID
     * @param request 수정할 리뷰 정보
     * @return 수정된 리뷰 정보
     */
     ReviewDto updateReview(Long reviewId, UpdateReview.Request request);

    /**
     * 리뷰 삭제
     * @param reviewId 삭제할 리뷰 ID
     */
     void deleteReview(Long reviewId);
}
