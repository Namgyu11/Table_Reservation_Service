package com.example.table_reservation_service.store.web;

import com.example.table_reservation_service.store.dto.CreateStore;
import com.example.table_reservation_service.store.dto.StoreDto;
import com.example.table_reservation_service.store.dto.UpdateStore;
import com.example.table_reservation_service.store.service.StoreService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    // 매장 등록

    /**
     * 매장 등록
     * @param request 매장 등록 정보
     * @return 매장 등록 완료 정보
     */
    @PostMapping("/partner/create")
    @PreAuthorize("hasRole('PARTNER')")
    public CreateStore.Response createStore(@RequestBody CreateStore.Request request){
        return CreateStore.Response.fromDto(this.storeService.createStore(request));
    }

    /**
     * 매장 수정
     * @param id 매장 ID
     * @param request 수정할 매장 정보
     * @return 수정된 매장 정보
     */
    @PutMapping("/partner/update/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateStore.Response updateStore(@PathVariable Long id,
                                            @RequestBody UpdateStore.Request request){
        return UpdateStore.Response.fromDto(this.storeService.updateStore(id,request));
    }

    /**
     * 매장 삭제
      * @param managerId 매장 매니저 ID
     * @param storeId 매장 ID
     * @return 매장 삭제 완료 메세지
     */
    @DeleteMapping("/partner/delete/")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> deleteStore(@RequestParam("managerId") Long managerId,
                                         @RequestParam("storeId") Long storeId){
        this.storeService.deleteStore(managerId,storeId);
        return ResponseEntity.ok("매장 삭제 완료");
    }

    /**
     * 매장 상세 정보
     * @param name 매장 이름
     * @return  해당 매장 상세 정보
     */
    @GetMapping("/detail/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> detailStore(@PathVariable String name){
        return ResponseEntity.ok(this.storeService.detailStore(name));
    }

    // 매장 리스트
    @GetMapping("/list")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> searchStoreList(){
        List<StoreDto> storeDtoList = this.storeService.searchStoreList();
        return ResponseEntity.ok(storeDtoList);
    }

}
