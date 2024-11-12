package com.example.Library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.User;
import com.example.Library.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException();

        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
