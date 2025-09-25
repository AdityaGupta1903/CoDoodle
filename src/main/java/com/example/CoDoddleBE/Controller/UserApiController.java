package com.example.CoDoddleBE.Controller;

import com.example.CoDoddleBE.Entities.UserAuthEntity;
import com.example.CoDoddleBE.Repository.UserAuthEntityRepository;
import com.example.CoDoddleBE.Service.UserAuthEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserApiController {
    @Autowired
    private UserAuthEntityService userAuthEntityService;

    @GetMapping("/GetUser/{userId}")
    public Optional<UserAuthEntity> getUserDetail(@PathVariable Long userId){
        return userAuthEntityService.getUser(userId);
    }
}
