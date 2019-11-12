package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

    List<State> getAllBy();
    State getByStateId(UUID locationId);
    void deleteAllBy();
    void deleteByStateId(UUID locationId);
}
