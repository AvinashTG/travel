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

    public DriverDetails updateDriverDetails(DriverDetails inputDriverDetails) {
        log.info("Updating driver details: {}", inputDriverDetails);

        // Check if the driver details exist before updating
        if (Objects.isNull(inputDriverDetails.getId())) {
            throw new IllegalArgumentException("Driver details ID must not be null for update");
        }

        final var existingDetails = driverDetailsRepo.findById(inputDriverDetails.getId())
                .orElseThrow(() -> new RuntimeException("Driver details not found for ID: " + inputDriverDetails.getId()));
        // Check if existing details license number matches
        if (!existingDetails.getLicenseNumber().equals(inputDriverDetails.getLicenseNumber())) {
            throw new IllegalArgumentException("License number doesn't match for ID: " + inputDriverDetails.getId());
        }

        // Throw error if expiry date is in the past
        if (Objects.nonNull(inputDriverDetails.getExpiryDate()) && inputDriverDetails.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past");
        }

        // Throw error if date of birth is in the future
        if (Objects.nonNull(inputDriverDetails.getDob()) && inputDriverDetails.getDob().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        // Update dob if provided
        if (Objects.nonNull(inputDriverDetails.getDob())) {
            existingDetails.setDob(inputDriverDetails.getDob());
        }
        // Update expiry date if provided
        if (Objects.nonNull(inputDriverDetails.getExpiryDate())) {
            existingDetails.setExpiryDate(inputDriverDetails.getExpiryDate());
        }
        // Update gender if provided
        if (Objects.nonNull(inputDriverDetails.getGender())) {
            existingDetails.setGender(inputDriverDetails.getGender());
        }


        return driverDetailsRepo.save(existingDetails);
    }
}
