package com.srt.service;

import com.srt.entities.DriverDetails;
import com.srt.repository.DriverDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverDetailsService {

    private final DriverDetailsRepo driverDetailsRepo;

    public Long saveDriverDetails(DriverDetails driverDetails) {
        log.info("Saving driver details: {}", driverDetails);
        final var saved = driverDetailsRepo.save(driverDetails);
        log.info("Saved driver details with ID: {}", saved.getId());
        return saved.getId();
    }
}
