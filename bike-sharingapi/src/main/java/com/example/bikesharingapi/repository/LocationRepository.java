package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    List<Location> getAllBy();
    Location getByLocationId(UUID locationId);
    void deleteAllBy();
    void deleteByLocationId(UUID locationId);
}
