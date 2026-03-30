package com.game.SoloFighter.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.game.SoloFighter.model.Users;
import com.game.SoloFighter.repo.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService{

    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (username == null){
            throw new UsernameNotFoundException("No user found");
        }
        return user;
    }
    
}
