package com.example.table_reservation_service.store.dto;

import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class CreatStore {

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

        public Store toEntity(Manager manager){
           return Store.builder()
                    .manager(manager)
                    .storeName(this.getStoreName())
                    .location(this.getLocation())
                    .phoneNumber(this.getPhoneNumber())
                    .build();
        }


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String storeName;

        public static Response from(StoreDto storeDto){
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .build();
        }

    }
}
