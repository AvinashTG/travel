package com.srt.controller;

import com.srt.entities.DriverDetails;
import com.srt.service.DriverDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DriverDetailsAPI {

    private final DriverDetailsService driverDetailsService;

    @PostMapping("/add-driver-details")
    public Long addDriverDetails(@RequestBody DriverDetails driverDetails) {
        log.info("Driver Details: {}", driverDetails);
        Long id = driverDetailsService.saveDriverDetails(driverDetails);
        log.info("Driver details saved with ID: {}", id);
        return id;
    }
}
