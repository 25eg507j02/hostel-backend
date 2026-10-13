package com.hostel.vendor.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.GatePass;
import com.hostel.vendor.repository.GatePassRepository;

@Service
public class GatePassService {

    private final GatePassRepository repository;

    public GatePassService(GatePassRepository repository) {
        this.repository = repository;
    }

    public GatePass requestGatePass(GatePass gatePass) {
        gatePass.setStatus("PENDING");
        if (gatePass.getCreatedAt() == null) {
            gatePass.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(gatePass);
    }

    public List<GatePass> getAllGatePasses() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public List<GatePass> getGatePassesByStudent(String email) {
        return repository.findByStudentEmailOrderByCreatedAtDesc(email);
    }

    public GatePass getGatePass(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("GatePass not found with id: " + id));
    }

    public GatePass updateGatePassStatus(Long id, String status, String remarks) {
        GatePass gatePass = getGatePass(id);
        gatePass.setStatus(status.toUpperCase());
        gatePass.setWardenRemarks(remarks);
        gatePass.setReviewedAt(LocalDateTime.now());
        return repository.save(gatePass);
    }

    public void deleteGatePass(Long id) {
        repository.delete(getGatePass(id));
    }
}
