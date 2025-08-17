package com.srt.repository;

import com.srt.entities.DriverDetails;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface DriverDetailsRepo extends JpaRepository<DriverDetails, Long> {
}
