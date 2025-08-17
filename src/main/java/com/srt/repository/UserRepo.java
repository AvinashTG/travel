package com.srt.repository;

import com.srt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {

    @Query(nativeQuery = true,
            value = "select * from app_user where username=:username limit 1")
    AppUser findUserByUsername(String username);
}
