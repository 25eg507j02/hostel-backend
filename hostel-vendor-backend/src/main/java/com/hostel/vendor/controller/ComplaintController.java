package com.hostel.vendor.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.hostel.vendor.entity.Complaint;
import com.hostel.vendor.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping
    public Complaint add(@RequestBody Complaint complaint) {
        return service.addComplaint(complaint);
    }

    @GetMapping
    public List<Complaint> getAll() {
        return service.getAllComplaints();
    }

    @GetMapping("/{id}")
    public Complaint getOne(@PathVariable Long id) {
        return service.getComplaint(id);
    }

    @PutMapping("/{id}")
    public Complaint update(
            @PathVariable Long id,
            @RequestBody Complaint complaint) {
        return service.updateComplaint(id, complaint);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteComplaint(id);
        return "Complaint deleted successfully";
    }
}
