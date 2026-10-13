package com.hostel.vendor.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.hostel.vendor.entity.Warden;
import com.hostel.vendor.service.WardenService;

@RestController
@RequestMapping("/api/wardens")
public class WardenController {

    private final WardenService service;

    public WardenController(WardenService service) {
        this.service = service;
    }

    @PostMapping
    public Warden addWarden(@RequestBody Warden warden) {
        return service.addWarden(warden);
    }

    @GetMapping
    public List<Warden> getAllWardens() {
        return service.getAllWardens();
    }

    @GetMapping("/{id}")
    public Warden getWardenById(@PathVariable Long id) {
        return service.getWardenById(id);
    }

    @PutMapping("/{id}")
    public Warden updateWarden(
            @PathVariable Long id,
            @RequestBody Warden warden) {
        return service.updateWarden(id, warden);
    }

    @DeleteMapping("/{id}")
    public String deleteWarden(@PathVariable Long id) {
        service.deleteWarden(id);
        return "Warden deleted successfully";
    }
}