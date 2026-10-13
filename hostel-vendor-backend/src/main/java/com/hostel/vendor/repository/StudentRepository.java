package com.hostel.vendor.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.vendor.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
