package com.srt.service;

import com.srt.entities.VehicleDetails;
import com.srt.repository.VehicleDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleDetailsService {
    private final VehicleDetailsRepo vehicleDetailsRepo;

    public VehicleDetails addVehicle(VehicleDetails vehicleDetails) {
        log.debug("addVehicle(): {}", vehicleDetails);
        final VehicleDetails saved = vehicleDetailsRepo.save(vehicleDetails);
        log.debug("vehicle detail saved: {}", saved);
        return saved;
    }

    public VehicleDetails updateVehicleDetails(VehicleDetails input) {
        VehicleDetails existing = vehicleDetailsRepo.findByRegNumber(input.getRegNumber())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vehicle with register number " + input.getRegNumber() + " not found."
                ));

        LocalDate today = LocalDate.now();

        // Validate expiry dates (only if provided)
        if (input.getRcExpiryDate() != null && input.getRcExpiryDate().isBefore(today)) {
            throw new IllegalArgumentException("Warning: RC expiry date cannot be in the past.");
        }
        if (input.getTaxExpiryDate() != null && input.getTaxExpiryDate().isBefore(today)) {
            throw new IllegalArgumentException("Warning: Tax expiry date cannot be in the past.");
        }
        if (input.getInsuranceExpiryDate() != null && input.getInsuranceExpiryDate().isBefore(today)) {
            throw new IllegalArgumentException("Warning: Insurance expiry date cannot be in the past.");
        }

        // Update only the fields you allow
        if (input.getAc() != null) existing.setAc(input.getAc());
        if (input.getRcExpiryDate() != null) existing.setRcExpiryDate(input.getRcExpiryDate());
        if (input.getTaxExpiryDate() != null) existing.setTaxExpiryDate(input.getTaxExpiryDate());
        if (input.getInsuranceExpiryDate() != null) existing.setInsuranceExpiryDate(input.getInsuranceExpiryDate());
        if (input.getStatus() != null) existing.setStatus(input.getStatus());

        // Do NOT touch name, modelYear, noOfSeats
        return vehicleDetailsRepo.save(existing);
    }
}