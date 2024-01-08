package com.example.table_reservation_service.store.dto;

import com.example.table_reservation_service.store.entity.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreList {
    private String storeName;
    private String location;
    private String phoneNumber;
    /**
     * 일반 유저가 매장을 검색할 때
     * @param store
     * @return
     */
    public static StoreList searchStore(Store store){
        return StoreList.builder()
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }
}
