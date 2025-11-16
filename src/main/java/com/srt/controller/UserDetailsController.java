package com.srt.controller;

import com.srt.entities.UserDetails;
import com.srt.model.ServerSideGetRowsRequest;
import com.srt.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-details")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    @GetMapping("/get-all")
    public List<UserDetails> getUserDetails(){
        log.debug("user details");
        return userDetailsService.getUserDetails();
    }

    @PostMapping("/add-user")
    public UserDetails addUser(@RequestBody UserDetails userDetails){
        log.debug("User Details");
        return userDetailsService.addUser(userDetails);
    }

    @PostMapping("/by-page-request")
    public Page<UserDetails> getUserDetailsByPageRequest(@RequestBody ServerSideGetRowsRequest request) {
        log.debug("User Details by page request");
        return userDetailsService.getUserDetailsByPageRequest(request);
    }
}
