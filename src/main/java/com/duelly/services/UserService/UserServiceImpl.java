package com.duelly.services.UserService;

import com.duelly.entities.User;
import com.duelly.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User getUserInfo(String email){
        Optional<User> foundUser = userRepository.findFirstByEmail(email);
        if(foundUser.isPresent()){
            return foundUser.get();
        }
        return null;
    }

    public User getUserInfo(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isPresent()){
            return foundUser.get();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findFirstByEmail(email);
        log.info(foundUser.get().getEmail());
        if(foundUser.isEmpty()){
            throw new UsernameNotFoundException("User not found", null);
        }
        return new org.springframework.security.core.userdetails.User(foundUser.get().getEmail(), foundUser.get().getPassword(), new ArrayList<>());
    }

    @Override
    public User validateUser(String email, String password) {
        Optional<User> user = userRepository.findFirstByEmail(email);
        if(user.isPresent()){
            log.info(password + user.get().getPassword());
            BCrypt.checkpw(password, user.get().getPassword());
        }
        return null;
    }
}
