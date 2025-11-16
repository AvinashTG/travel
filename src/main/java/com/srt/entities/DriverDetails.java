package com.srt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.srt.utils.AppConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "driver_details")
public class DriverDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driverSeqGen")
    @SequenceGenerator(name = "driverSeqGen", sequenceName = "driver_details_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.dateFormat)
    private LocalDate expiryDate;
    @Column(name = "dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.dateFormat)
    private LocalDate dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "user_id")
    private Long userId;
}
