package com.myslot.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AddRoom {

    @Id
    private Long roomNo;

    private String roomName;
    private int perHour;
    private int capacity;

    public AddRoom() {}

    public AddRoom(Long roomNo, String roomName, int perHour, int capacity){
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.perHour = perHour;
        this.capacity = capacity;
    }

    public Long getRoomNo() { return roomNo; }
    public void setRoomNo(Long roomNo) { this.roomNo = roomNo; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public int getPerHour() { return perHour; }
    public void setPerHour(int perHour) { this.perHour = perHour; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
