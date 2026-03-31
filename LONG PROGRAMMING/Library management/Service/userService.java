package com.example.libaraies.Service;

import com.example.libaraies.DHO.user;

import com.example.libaraies.Repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private userRepository userRepo;


    public List<user> getallusers() {
        return userRepo.findAll();
    }


    public user saveuser(user User) {
        return userRepo.save(User);
    }


    public user updateuser(int id, user user) {
        user existing = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existing.setUserid(user.getUserid());
        existing.setUsername(user.getUsername());

        return userRepo.save(existing);
    }


    public String deleteuser(int id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepo.deleteById(id);
        return "User deleted successfully";
    }
}