package com.example.table_reservation_service.review.service;

import com.example.table_reservation_service.customer.entity.Customer;
import com.example.table_reservation_service.customer.repository.CustomerRepository;
import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.reservation.entity.Reservation;
import com.example.table_reservation_service.reservation.repository.ReservationRepository;
import com.example.table_reservation_service.reservation.type.ReservationStatus;
import com.example.table_reservation_service.review.dto.CreateReview;
import com.example.table_reservation_service.review.dto.ReviewDto;
import com.example.table_reservation_service.review.dto.ReviewRequest;
import com.example.table_reservation_service.review.dto.UpdateReview;
import com.example.table_reservation_service.review.entity.Review;
import com.example.table_reservation_service.review.repository.ReviewRepository;
import com.example.table_reservation_service.store.entity.Store;
import com.example.table_reservation_service.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.table_reservation_service.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReviewDto createReview(Long userId, Long storeId, Long reservationId,
                                  CreateReview.Request request) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));
        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new GlobalException(STORE_NOT_FOUND));
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(RESERVATION_NOT_FOUND));

        validationReviewStatus(customer, reservation);
        validationReviewLimit(request);

        Review reviewInfo = this.reviewRepository.save(Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .customer(customer)
                .store(store)
                .reservation(reservation)
                .build());

        return ReviewDto.fromEntity(reviewInfo);
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long reviewId, UpdateReview.Request request) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(REVIEW_NOT_FOUND));

        validationReviewLimit(request);
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        Review reviewInfo = this.reviewRepository.save(review);

        return ReviewDto.fromEntity(reviewInfo);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(REVIEW_NOT_FOUND));

        this.reviewRepository.delete(review);
    }

    /**
     * 리뷰 관련 유효성 검사
     * 1. 예약 및 사용한 고객과 일치하는 ID가 있는 경우
     * 2. 해당 예약에 대한 리뷰가 존재하는 경우
     * 3. 리뷰를 예약 및 사용 이전에 작성하는 경우
     *
     * @param customer    고객 ID
     * @param reservation 해당 예약 ID
     */
    public void validationReviewStatus(Customer customer, Reservation reservation) {
        if (!reservation.getCustomer().getId().equals(customer.getId())) {
            throw new GlobalException(USER_AUTHORITY_NOT_MATCH);
        }
        if (this.reviewRepository.existsByReservationId(reservation.getId())) {
            throw new GlobalException(ALREADY_EXIST_REVIEW);
        }
        if (!reservation.getReservationStatus().equals(ReservationStatus.USE_COMPLETED)) {
            throw new GlobalException(REVIEW_NOT_AVAILABLE);
        }
    }

    /**
     * 리뷰 작성(수정)시 유효성 검사
     * 1. 별점은 범위가 1 ~ 5가 아닌 경우
     * 2. 텍스트의 길이는 300 이하가 아닌 경우
     *
     * @param request
     */
    public void validationReviewLimit(ReviewRequest request) {
        if (request.getRating() < 0 || request.getRating() > 5) {
            throw new GlobalException(REVIEW_RATING_RANGE_OVER);
        }
        if (request.getContent().length() > 300) {
            throw new GlobalException(REVIEW_TEXT_TOO_LONG);
        }
    }
}

