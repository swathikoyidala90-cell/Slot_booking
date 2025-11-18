package com.myslot.booking;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface mybookingRepo extends JpaRepository<my_slot_booking,String>{
     List<my_slot_booking> findByRoomNoAndEndTimeAfterAndStartTimeBefore(Long roomNo, LocalDateTime start, LocalDateTime end);
}
