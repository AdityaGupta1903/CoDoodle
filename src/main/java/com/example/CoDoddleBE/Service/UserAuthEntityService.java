package com.example.CoDoddleBE.Service;

import com.example.CoDoddleBE.Entities.UserAuthEntity;
import com.example.CoDoddleBE.Repository.UserAuthEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthEntityService implements UserDetailsService {

    @Autowired
    private UserAuthEntityRepository userAuthEntityRepository;

    public UserDetails save(UserAuthEntity userAuth){
        return userAuthEntityRepository.save(userAuth);
    }

    @Override
    public UserAuthEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthEntityRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    public Optional<UserAuthEntity> getUser(Long id){
        return userAuthEntityRepository.findById(id);
    }
}
