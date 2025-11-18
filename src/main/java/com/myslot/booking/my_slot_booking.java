package com.myslot.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class my_slot_booking {

    @Id
    private String regNo;

    private String name;
    private Long roomNo;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalPay;

    public my_slot_booking() {}

    public my_slot_booking(String regNo, String name, Long roomNo, LocalDateTime startTime, LocalDateTime endTime, int totalPay){
        this.regNo = regNo;
        this.name = name;
        this.roomNo = roomNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPay = totalPay;
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getRoomNo() { return roomNo; }
    public void setRoomNo(Long roomNo) { this.roomNo = roomNo; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public int getTotalPay() { return totalPay; }
    public void setTotalPay(int totalPay) { this.totalPay = totalPay; }
}
