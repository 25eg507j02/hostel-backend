package com.hostel.vendor.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.Student;
import com.hostel.vendor.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(Student student) {
        if (student.getFeeStatus() == null || student.getFeeStatus().isBlank()) {
            student.setFeeStatus("PENDING");
        }
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudent(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Student not found: " + id));
    }

    public Optional<Student> getStudentByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Student updateStudent(Long id, Student updated) {
        Student student = getStudent(id);

        if (updated.getName() != null) student.setName(updated.getName());
        if (updated.getEmail() != null) student.setEmail(updated.getEmail());
        if (updated.getPhone() != null) student.setPhone(updated.getPhone());
        if (updated.getRoomNumber() != null) student.setRoomNumber(updated.getRoomNumber());
        if (updated.getHostelName() != null) student.setHostelName(updated.getHostelName());
        if (updated.getFeeAmount() != null) student.setFeeAmount(updated.getFeeAmount());
        if (updated.getFeeStatus() != null) student.setFeeStatus(updated.getFeeStatus());

        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.delete(getStudent(id));
    }
}
