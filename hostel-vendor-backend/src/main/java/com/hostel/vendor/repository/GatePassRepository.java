package com.hostel.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.vendor.entity.GatePass;
import java.util.List;

public interface GatePassRepository extends JpaRepository<GatePass, Long> {
    List<GatePass> findAllByOrderByCreatedAtDesc();
    List<GatePass> findByStudentEmailOrderByCreatedAtDesc(String studentEmail);
}
