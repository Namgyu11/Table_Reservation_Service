package com.example.table_reservation_service.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateReview {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements ReviewRequest {
        private String content;
        private double rating;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reviewId;
        private String content;
        private double rating;

        public static Response fromDto(ReviewDto reviewDto) {
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .content(reviewDto.getContent())
                    .rating(reviewDto.getRating())
                    .build();
        }
    }
}
