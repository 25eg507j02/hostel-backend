package com.hostel.vendor.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.Notice;
import com.hostel.vendor.repository.NoticeRepository;

@Service
public class NoticeService {

    private final NoticeRepository repository;

    public NoticeService(NoticeRepository repository) {
        this.repository = repository;
    }

    public Notice addNotice(Notice notice) {
        if (notice.getCreatedAt() == null) {
            notice.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(notice);
    }

    public List<Notice> getAllNotices() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public Notice getNotice(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id));
    }

    public Notice updateNotice(Long id, Notice updated) {
        Notice notice = getNotice(id);

        if (updated.getTitle() != null) {
            notice.setTitle(updated.getTitle());
        }
        if (updated.getContent() != null) {
            notice.setContent(updated.getContent());
        }
        if (updated.getCategory() != null) {
            notice.setCategory(updated.getCategory());
        }
        notice.setUrgent(updated.isUrgent());
        if (updated.getPostedBy() != null) {
            notice.setPostedBy(updated.getPostedBy());
        }

        return repository.save(notice);
    }

    public void deleteNotice(Long id) {
        repository.delete(getNotice(id));
    }
}
