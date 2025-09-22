package com.srt.controller;

import com.srt.entities.VehicleDetails;
import com.srt.service.VehicleDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class VehicleDetailsController {
    private final VehicleDetailsService vehicleDetailsService;

    @PostMapping("/add-vehicle-details")
    public VehicleDetails addVehicle(@RequestBody VehicleDetails vehicleDetails){
        log.debug("vehicle details:");
        return vehicleDetailsService.addVehicle(vehicleDetails);
    }

    @PostMapping("/update-vehicle-details")
    public VehicleDetails updateVehicleDetails(@RequestBody VehicleDetails vehicleDetails){
        log.debug("updateVehicleDetails()");
        return vehicleDetailsService.updateVehicleDetails(vehicleDetails);
    }
}
