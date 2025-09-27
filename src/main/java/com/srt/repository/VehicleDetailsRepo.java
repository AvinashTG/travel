package com.srt.repository;

import com.srt.entities.VehicleDetails;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails, Long> {
    Optional<VehicleDetails> findByRegNumber(String regNumber);
}
