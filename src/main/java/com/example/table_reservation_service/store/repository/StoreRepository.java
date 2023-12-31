package com.example.table_reservation_service.store.repository;

import com.example.table_reservation_service.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByStoreName(String name);

    Optional<Store> findByStoreName(String storeName);

    /**
     * 매장 매니저가 등록한 매장의 정보
     *
     * @param managerId 매장 매니저 ID
     * @return List<Store>
     */
    @Query(" select s from Store s where s.manager.id = :managerId")
    List<Store> findStoreByMangerId(@Param("managerId") Long managerId);
}
