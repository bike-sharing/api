package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, UUID> {

    List<Bicycle> getAllBy();
    Bicycle getByBicycleId(UUID bicycleId);
    void deleteByBicycleIdIs(UUID bicycleId);
    void deleteAllBy();
    List<Bicycle> getBicyclesByLocation_LocationId(UUID locationId);
}
