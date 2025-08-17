package com.srt.service;

import com.srt.entities.DriverDetails;
import com.srt.repository.DriverDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

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

    public DriverDetails updateDriverDetails(DriverDetails driverDetails) {
        log.info("Updating driver details: {}", driverDetails);

        // Check if the driver details exist before updating
        if (Objects.isNull(driverDetails.getId())) {
            throw new IllegalArgumentException("Driver details ID must not be null for update");
        }

        final var existingDetails = driverDetailsRepo.findById(driverDetails.getId())
                .orElseThrow(() -> new RuntimeException("Driver details not found for ID: " + driverDetails.getId()));
        // Check if existing details license number matches
        if (!existingDetails.getLicenseNumber().equals(driverDetails.getLicenseNumber())) {
            throw new IllegalArgumentException("License number doesn't match for ID: " + driverDetails.getId());
        }

        // Throw error if expiry date is in the past
        if (Objects.nonNull(driverDetails.getExpiryDate()) && driverDetails.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }
        existingDetails.setExpiryDate(driverDetails.getExpiryDate());

        // Throw error if date of birth is in the future
        if (Objects.nonNull(driverDetails.getDob()) && driverDetails.getDob().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        existingDetails.setDob(driverDetails.getDob());
        existingDetails.setGender(driverDetails.getGender());
        existingDetails.setUserId(driverDetails.getUserId());
        return driverDetailsRepo.save(existingDetails);
    }
}
