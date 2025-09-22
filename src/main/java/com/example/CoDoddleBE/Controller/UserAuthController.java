package com.example.CoDoddleBE.Controller;

import com.example.CoDoddleBE.Entities.UserAuthEntity;
import com.example.CoDoddleBE.Service.UserAuthEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthEntityService userAuthEntityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthEntity userAuthDetails){

        // Hash the password before saving
        userAuthDetails.setPassword(passwordEncoder.encode(userAuthDetails.getPassword()));

        userAuthEntityService.save(userAuthDetails);
        return ResponseEntity.ok("User Saved Successfully");
    }

    @GetMapping("/GetUser/{userId}")
    public Optional<UserAuthEntity> getUser(@PathVariable Long userId){
        return userAuthEntityService.getUser(userId);
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello";
    }

}
