package com.srt.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "user_details")
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "status")
    private String status;
}
