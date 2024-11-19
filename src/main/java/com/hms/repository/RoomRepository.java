package com.hms.repository;

import com.hms.entity.Property;
import com.hms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByTypeAndProperty(String type, Property property);
}