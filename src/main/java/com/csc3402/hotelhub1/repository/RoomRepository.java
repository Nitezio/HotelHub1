package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findFirstByRoomType_RoomTypeIdAndStatus(Long roomTypeId, String status);

    boolean existsByRoomNumber(String roomNumber);

    // NEW: Used to check uniqueness when editing
    boolean existsByRoomNumberAndRoomIdNot(String roomNumber, Long roomId);
}
