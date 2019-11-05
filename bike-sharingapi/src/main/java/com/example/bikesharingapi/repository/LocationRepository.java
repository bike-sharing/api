package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    List<Location> getAllBy();
    List<Location> getByLocationId(UUID locationId);
    void deleteAllBy();
    void deleteByLocationId(UUID locationId);
}
