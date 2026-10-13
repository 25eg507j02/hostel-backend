
package com.hostel.vendor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.vendor.entity.HostelUser;

public interface HostelUserRepository
        extends JpaRepository<HostelUser, Long> {

    Optional<HostelUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
