package com.hostel.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.vendor.entity.Notice;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByCreatedAtDesc();
}
