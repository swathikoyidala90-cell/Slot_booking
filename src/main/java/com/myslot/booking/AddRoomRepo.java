package com.myslot.booking;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddRoomRepo extends JpaRepository<AddRoom, Long> {
    Optional<AddRoom> findByRoomNo(Long roomNo);
}
