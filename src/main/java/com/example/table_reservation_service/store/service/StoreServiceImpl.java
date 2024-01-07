package com.example.table_reservation_service.store.service;

import com.example.table_reservation_service.global.exception.GlobalException;
import com.example.table_reservation_service.manager.entity.Manager;
import com.example.table_reservation_service.manager.repository.ManagerRepository;
import com.example.table_reservation_service.store.dto.CreateStore;
import com.example.table_reservation_service.store.dto.StoreDto;
import com.example.table_reservation_service.store.dto.UpdateStore;
import com.example.table_reservation_service.store.entity.Store;
import com.example.table_reservation_service.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.table_reservation_service.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    /**
     * 매장 등록
     *
     * @param request 매장 상세 정보 및 매니저 아이디를 갖는 객체
     * @return 매장 상세 정보를 갖는 객체
     */
    @Override
    @Transactional
    public StoreDto createStore(CreateStore.Request request) {
        log.info("==== 매장 생성 =====");

        // 매니저 ID 찾기
        Manager manager = this.managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new GlobalException(MANAGER_NOT_FOUND));

        // 이미 생성된 매장이 있는지 확인
        if (this.storeRepository.existsByStoreName(request.getStoreName())) {
            throw new GlobalException(ALREADY_EXIST_STORE);
        }

        log.info("***** 매장 생성 완료 *****");

        return StoreDto.fromEntity(this.storeRepository.save(request.toEntity(manager)));
    }


    /**
     * 매장 수정
     *
     * @param storeId 매장 ID
     * @param request 변경하려는 매장 정보
     * @return 변경된 매장 정보
     */
    @Override
    @Transactional
    public StoreDto updateStore(Long storeId, UpdateStore.Request request) {
        log.info("==== 매장 수정 =====");

        // 매장이 있는지 확인
        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new GlobalException(STORE_NOT_MATCH_MANAGER));

        // 매장 매니저 본인의 매장인지 확인
        if (!store.getManager().getId().equals(request.getManagerId())) {
            throw new GlobalException(STORE_NOT_MATCH_MANAGER);
        }

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());
        store.setPhoneNumber(request.getPhoneNumber());

        log.info("***** 매장 정보 수정 완료 *****");

        return StoreDto.fromEntity(this.storeRepository.save(store));
    }

    /**
     * 매장 삭제
     */
    /**
     * 매장 삭제
     *
     * @param manageId 해당 매장 매니저 ID
     * @param storeId  해당 매장 ID
     */
    @Override
    @Transactional
    public void deleteStore(Long manageId, Long storeId) {
        log.info("==== 매장 삭제 =====");

        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new GlobalException(STORE_NOT_FOUND));

        // 매장 매니저 본인의 매장인지 확인
        if (!store.getManager().getId().equals(manageId)) {
            throw new GlobalException(STORE_NOT_MATCH_MANAGER);
        }

        this.storeRepository.delete(store);
        log.info("***** 매장 삭제 완료 *****");
    }

    /**
     * 매장 상세 정보
     * @param name 매장 이름
     * @return 매장 세부정보
     */
    @Override
    public StoreDto detailStore(String name) {
        log.info("==== 매장 세부 정보 =====");

        Store store = checkStoreName(name);
        return StoreDto.fromEntity(store);

    }

    /**
     * 매장 검색
     * @return 매장 리스트
     */
    @Override
    public List<StoreDto> searchStoreList() {
        log.info("==== 매장 리스트 확인 =====");

        List<Store> stores = this.storeRepository.findAll();
        if(stores.isEmpty()){
            throw new GlobalException(STORE_NOT_FOUND);
        }
        log.info("==== 매장 리스트 확인 완료=====");

        return stores.stream().map(StoreDto::searchStore).collect(Collectors.toList());
    }

    private Store checkStoreName(String name) {
        return this.storeRepository.findByStoreName(name)
                .orElseThrow(() -> new GlobalException(STORE_NOT_FOUND));
    }
}
