package com.example.table_reservation_service.store.service;


import com.example.table_reservation_service.store.dto.CreatStore;
import com.example.table_reservation_service.store.dto.StoreDto;
import com.example.table_reservation_service.store.dto.UpdateStore;

import java.util.List;

public interface StoreService  {

    /**
     * 매장 등록
     */
    public StoreDto creatStore(CreatStore.Request request);

    /**
     * 매장 수정
     */
    public StoreDto updateStore(Long storeId, UpdateStore.Request request);

    /**
     * 매장 삭제
     */
    public void deleteStore(Long manageId, Long storeId);

    /**
     * 매장 상세 정보
     */
    public StoreDto detailStore(String name);

    /**
     * 매장 검색
     */
    public List<StoreDto> searchStoreList();

}
