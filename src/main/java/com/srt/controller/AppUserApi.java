package com.srt.controller;

import com.srt.entities.AppUser;
import com.srt.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppUserApi {
    private final AppUserService appUserService;

    @PostMapping("/add-user")
    public AppUser addNewUser(@RequestBody AppUser appUser) {
        return appUserService.addUser(appUser);
    }

    @PostMapping("/authenticate-user")
    public boolean authenticateUser(@RequestBody AppUser appUser) {
        return appUserService.authenticateUser(appUser.getUsername(), appUser.getPassword());
    }
}
