package com.srt.service;

import com.srt.entities.AppUser;
import com.srt.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final UserRepo userRepo;

    public AppUser addUser(AppUser appUser) {
        //logic to add user in database using userRepo
        final var savedUser = userRepo.save(appUser);
        //return saved user
        return savedUser;
    }

    public boolean authenticateUser(String username, String password) {
        final var user = userRepo.findUserByUsername(username);
        return user.getPassword().equals(password);
    }
}
