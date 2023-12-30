package com.example.table_reservation_service.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Data JPA 의 Auditing 기능을 활성화하는 역할
 * 애플리케이션의 데이터 생성, 수정 등의 이력을 자동으로 추적하고 관리
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
