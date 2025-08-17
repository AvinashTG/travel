package com.srt.service;

import com.srt.entities.VehicleDetails;
import com.srt.repository.VehicleDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleDetailsService {
    private final VehicleDetailsRepo vehicleDetailsRepo;

    public VehicleDetails addVehicle(VehicleDetails vehicleDetails){
        log.debug("addVehicle(): {}", vehicleDetails);
        final VehicleDetails saved = vehicleDetailsRepo.save(vehicleDetails);
        log.debug("vehicle detail saved: {}", saved);
        return saved;
    }
}
