package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
