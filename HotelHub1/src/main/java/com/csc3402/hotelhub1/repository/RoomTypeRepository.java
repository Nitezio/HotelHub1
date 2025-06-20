package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}