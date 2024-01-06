package com.example.table_reservation_service.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateStore {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long managerId;

        @NotBlank
        private String storeName;

        @NotBlank
        private String location;

        @NotBlank
        private String phoneNumber;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String storeName;
        private String location;
        private String phoneNumber;

        public static Response fromDto(StoreDto storeDto){
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .location(storeDto.getLocation())
                    .phoneNumber(storeDto.getPhoneNumber())
                    .build();
        }
    }
}
