package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

    List<State> getAllBy();
    List<State> getByStateId(UUID locationId);
    void deleteAllBy();
    void deleteByStateId(UUID locationId);
}
