package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

}