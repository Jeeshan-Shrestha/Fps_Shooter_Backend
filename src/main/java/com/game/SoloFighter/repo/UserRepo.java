package com.game.SoloFighter.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.game.SoloFighter.model.Users;

@Repository
public interface UserRepo extends MongoRepository<Users, String>{
    
    public Users findByUsername(String username);

}
