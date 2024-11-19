package com.example.Library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.Library.model.User;
import com.example.Library.model.Dto.UserCreateDto;
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
        
        if (id == null || id == 0) throw new IllegalArgumentException(); 

        return userRepository.findById(id).orElse(null);
    }

    public void createUser(UserCreateDto request){

        if (userVerification(request)) {

            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());

            user.setRegisterDate(LocalDate.now());
            user.setStatus(true);
            user.setLoans(new ArrayList<>());
            user.setSingleCard(UUID.randomUUID());
            userRepository.save(user);
        }
    }
    
    public void deleteUser(Integer id){

        if (id == null || id == 0) throw new IllegalArgumentException(); 

        userRepository.deleteById(id);
    }

    public Boolean userVerification(UserCreateDto user){

        if(user.getName() == null || user.getName().isBlank()) throw new IllegalArgumentException();
        if(user.getEmail() == null || user.getEmail().isBlank() || userRepository.existsByEmail(user.getEmail()) == true) throw new IllegalArgumentException();

        return true;
    }
}
