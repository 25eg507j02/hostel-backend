package com.hostel.vendor.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.Complaint;
import com.hostel.vendor.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository repository;

    public ComplaintService(ComplaintRepository repository) {
        this.repository = repository;
    }

    public Complaint addComplaint(Complaint complaint) {
        complaint.setStatus("PENDING");
        if (complaint.getCreatedAt() == null) {
            complaint.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    public Complaint getComplaint(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Complaint not found: " + id));
    }

    public Complaint updateComplaint(Long id, Complaint updated) {
        Complaint complaint = getComplaint(id);

        if (updated.getStatus() != null) {
            complaint.setStatus(updated.getStatus());
            if ("RESOLVED".equalsIgnoreCase(updated.getStatus()) && complaint.getResolvedAt() == null) {
                complaint.setResolvedAt(LocalDateTime.now());
            }
        }
        if (updated.getResolutionNotes() != null) {
            complaint.setResolutionNotes(updated.getResolutionNotes());
        }
        if (updated.getCategory() != null) {
            complaint.setCategory(updated.getCategory());
        }
        if (updated.getDescription() != null) {
            complaint.setDescription(updated.getDescription());
        }
        if (updated.getRoomNumber() != null) {
            complaint.setRoomNumber(updated.getRoomNumber());
        }
        if (updated.getHostelName() != null) {
            complaint.setHostelName(updated.getHostelName());
        }

        return repository.save(complaint);
    }

    public void deleteComplaint(Long id) {
        repository.delete(getComplaint(id));
    }
}
