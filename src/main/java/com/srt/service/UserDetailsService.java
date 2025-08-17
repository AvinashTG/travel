package com.srt.service;

import com.srt.entities.UserDetails;
import com.srt.repository.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserDetailsRepo userDetailsRepo;

    public List<UserDetails> getUserDetails(){
        final List<UserDetails> UserList = userDetailsRepo.findAll();
        return UserList;
    }

    public UserDetails addUser(UserDetails userDetails){
        log.debug("add User(): {}",userDetails);
        final UserDetails saved = userDetailsRepo.save(userDetails);
        log.debug("user detail saved {}",saved);
        return saved;
    }
}
