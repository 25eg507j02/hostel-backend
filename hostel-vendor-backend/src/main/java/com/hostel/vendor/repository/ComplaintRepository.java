
package com.hostel.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.vendor.entity.Complaint;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long> {
}
