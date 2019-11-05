package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BicycleRepository extends JpaRepository<Bicycle, UUID> {

    @Query("select b from bicycle b")
    List<Bicycle> getAll();
    List<Bicycle> getAllByAvailabilityIsTrue();
    List<Bicycle> getAllByAvailabilityIsFalse();
}
