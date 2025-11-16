package com.srt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.srt.utils.AppConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "vehicle_details")
@Data
public class VehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleSeqGen")
    @SequenceGenerator(name = "vehicleSeqGen", sequenceName = "vehicle_details_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "reg_number")
    private String regNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "model_year")
    private Long modelYear;
    @Column(name = "no_of_seats")
    private Long noOfSeats;
    @Column(name = "ac")
    private String ac;
    @Column(name = "tax_expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.dateFormat)
    private LocalDate taxExpiryDate;
    @Column(name = "rc_expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.dateFormat)
    private LocalDate rcExpiryDate;
    @Column(name = "insurance_expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.dateFormat)
    private LocalDate insuranceExpiryDate;
    @Column(name = "status")
    private String status;
}
