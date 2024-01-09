package com.example.table_reservation_service.store.service;


import com.example.table_reservation_service.store.dto.CreateStore;
import com.example.table_reservation_service.store.dto.StoreDto;
import com.example.table_reservation_service.store.dto.StoreList;
import com.example.table_reservation_service.store.dto.UpdateStore;

import java.util.List;

public interface StoreService  {

    /**
     * 매장 등록
     */
     StoreDto createStore(CreateStore.Request request);

    /**
     * 매장 수정
     */
     StoreDto updateStore(Long storeId, UpdateStore.Request request);

    /**
     * 매장 삭제
     */
     void deleteStore(Long manageId, Long storeId);

    /**
     * 매장 상세 정보
     */
     StoreDto detailStore(String name);

    /**
     * 매장 검색
     */
     List<StoreList> searchStoreList();

}
