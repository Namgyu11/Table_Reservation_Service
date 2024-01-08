package com.example.table_reservation_service.review.dto;

import com.example.table_reservation_service.review.entity.Review;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String content;
    private double rating;
    private String customerName;
    private String storeName;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .customerName(review.getCustomer().getUsername())
                .storeName(review.getStore().getStoreName())
                .build();
    }

}
