package com.hostel.vendor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String phone;
    private String roomNumber;
    private String hostelName;
    private Double feeAmount;
    private String feeStatus; // PAID, PENDING, OVERDUE

    public Student() {
        this.feeStatus = "PENDING";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getHostelName() { return hostelName; }
    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public Double getFeeAmount() { return feeAmount; }
    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeStatus() { return feeStatus; }
    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }
}
