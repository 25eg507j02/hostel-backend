
package com.hostel.vendor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.vendor.entity.AppUser;

public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
