package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for Room.
 *
 * Note: The RoomType entity has its primary key field named 'roomTypeId',
 * so the derived method below must reference that exact property name.
 */
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Finds the first available room of the given RoomType.
     *
     * @param roomTypeId the ID of the room type
     * @param status     the room status (e.g. "AVAILABLE")
     * @return a single Room or null if none found
     */
    Room findFirstByRoomType_RoomTypeIdAndStatus(Long roomTypeId, String status);
}
