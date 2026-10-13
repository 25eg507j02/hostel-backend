package com.hostel.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.Warden;
import com.hostel.vendor.repository.WardenRepository;

@Service
public class WardenService {

    private final WardenRepository repository;

    public WardenService(WardenRepository repository) {
        this.repository = repository;
    }

    public Warden addWarden(Warden warden) {
        return repository.save(warden);
    }

    public List<Warden> getAllWardens() {
        return repository.findAll();
    }

    public Warden getWardenById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Warden not found: " + id));
    }

    public Warden updateWarden(Long id, Warden updatedWarden) {
        Warden warden = getWardenById(id);

        warden.setName(updatedWarden.getName());
        warden.setEmail(updatedWarden.getEmail());
        warden.setPhone(updatedWarden.getPhone());
        warden.setHostelName(updatedWarden.getHostelName());
        warden.setAddress(updatedWarden.getAddress());

        return repository.save(warden);
    }

    public void deleteWarden(Long id) {
        Warden warden = getWardenById(id);
        repository.delete(warden);
    }
}