package com.hostel.vendor.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import com.hostel.vendor.entity.GatePass;
import com.hostel.vendor.service.GatePassService;

@RestController
@RequestMapping("/api/gatepasses")
public class GatePassController {

    private final GatePassService service;

    public GatePassController(GatePassService service) {
        this.service = service;
    }

    @PostMapping
    public GatePass request(@RequestBody GatePass gatePass) {
        return service.requestGatePass(gatePass);
    }

    @GetMapping
    public List<GatePass> getAll(@RequestParam(required = false) String studentEmail) {
        if (studentEmail != null && !studentEmail.isBlank()) {
            return service.getGatePassesByStudent(studentEmail);
        }
        return service.getAllGatePasses();
    }

    @GetMapping("/{id}")
    public GatePass getOne(@PathVariable Long id) {
        return service.getGatePass(id);
    }

    @PutMapping("/{id}/status")
    public GatePass updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String status = body.getOrDefault("status", "PENDING");
        String remarks = body.getOrDefault("remarks", "");
        return service.updateGatePassStatus(id, status, remarks);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteGatePass(id);
        return "GatePass deleted successfully";
    }
}
