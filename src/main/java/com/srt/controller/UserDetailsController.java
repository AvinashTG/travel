package com.srt.controller;

import com.srt.entities.UserDetails;
import com.srt.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    @GetMapping("/user-details")
    public List<UserDetails> getUserDetails(){
        log.debug("user details");
        return userDetailsService.getUserDetails();
    }

    @PostMapping("/add-user-details")
    public UserDetails addUser(@RequestBody UserDetails userDetails){
        log.debug("User Details");
        return userDetailsService.addUser(userDetails);
    }

}
