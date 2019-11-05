package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BicycleRepository extends JpaRepository<Bicycle, UUID> {

    List<Bicycle> getAllBy();
    Bicycle getByBicycleId(UUID bicycleId);
    boolean deleteByBicycleIdIs(UUID bicycleId);
    void deleteAllBy();
}
