package com.srt.controller;

import com.srt.entities.DriverDetails;
import com.srt.service.DriverDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DriverDetailsAPI {

    private final DriverDetailsService driverDetailsService;

    @GetMapping("/get-driver-details")
    public List<DriverDetails> getDriverDetails() {
        log.info("getDriverDetails()");
        return driverDetailsService.getAllDriverDetails();
    }

    @PostMapping("/add-driver-details")
    public Long addDriverDetails(@RequestBody DriverDetails driverDetails) {
        log.info("Driver Details: {}", driverDetails);
        Long id = driverDetailsService.saveDriverDetails(driverDetails);
        log.info("Driver details saved with ID: {}", id);
        return id;
    }

    @PostMapping("/update-driver-details")
    public DriverDetails updateDriverDetails(@RequestBody DriverDetails driverDetails) {
        log.info("updateDriverDetails()");
        return driverDetailsService.updateDriverDetails(driverDetails);
    }
}
