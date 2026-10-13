package com.hostel.vendor.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.hostel.vendor.entity.Notice;
import com.hostel.vendor.service.NoticeService;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    @PostMapping
    public Notice add(@RequestBody Notice notice) {
        return service.addNotice(notice);
    }

    @GetMapping
    public List<Notice> getAll() {
        return service.getAllNotices();
    }

    @GetMapping("/{id}")
    public Notice getOne(@PathVariable Long id) {
        return service.getNotice(id);
    }

    @PutMapping("/{id}")
    public Notice update(@PathVariable Long id, @RequestBody Notice notice) {
        return service.updateNotice(id, notice);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteNotice(id);
        return "Notice deleted successfully";
    }
}
