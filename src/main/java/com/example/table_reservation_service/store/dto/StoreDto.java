package com.example.table_reservation_service.store.dto;

import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.store.entity.Store;
import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private Manager manager;
    private String storeName;
    private String location;
    private String phoneNumber;

    public static StoreDto fromEntity(Store store){
        return StoreDto.builder()
                .manager(store.getManager())
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }

    /**
     * 일반 유저가 매장을 검색할 때
     * @param store
     * @return
     */
    public static StoreDto searchStore(Store store){
        return StoreDto.builder()
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }
}
