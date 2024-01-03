package com.example.table_reservation_service.customer.entity;

import com.example.table_reservation_service.auth.type.MemberType;
import com.example.table_reservation_service.global.entity.BaseEntity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends BaseEntity implements UserDetails {
    /**
     * 일반 회원 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 일반 회원 이름
     */
    @NotBlank
    private String username;

    /**
     *  일반 회원 구분
     */
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    /**
     *  일반 회원 이메일
     */
    @Email
    @Column(unique = true)
    private String email;

    /**
     * 일반 회원 비밀번호
     */
    @NotBlank
    private String password;

    /**
     *  일반 회원 전화번호
     */
    @NotBlank
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
