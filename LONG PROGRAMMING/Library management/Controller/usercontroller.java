package com.example.libaraies.Controller;

import com.example.libaraies.DHO.user;

import com.example.libaraies.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class usercontroller {

    @Autowired
    private userService userService;

    @GetMapping
    public List<user> getallusers() {
        return userService.getallusers();
    }

    @PostMapping
    public user createuser(@RequestBody user u) {
        return userService.saveuser(u);
    }

    @PutMapping("/{id}")
    public user updateuser(@PathVariable int id, @RequestBody user u) {
        return userService.updateuser(id, u);
    }

    @DeleteMapping("/{id}")
    public String deleteuser(@PathVariable int id) {
        return userService.deleteuser(id);
    }
}