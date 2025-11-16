package com.srt.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUserSeqGen")
    @SequenceGenerator(name = "appUserSeqGen", sequenceName = "app_user_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
