package com.example.billingsystem.Controller;

import com.example.billingsystem.DTO.billing;
import com.example.billingsystem.Service.billingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
@CrossOrigin(origins = "*")
public class billingcontroller {

    @Autowired
    private billingservice service;

    
    @GetMapping("/getbilling")
    public List<billing> getAllBilling() {
        return service.getAllBilling();
    }

    
    @PostMapping("/postbilling")
    public billing addBilling(@RequestBody billing b) {
        return service.addBilling(b);
    }

    
    @DeleteMapping("/{id}")
    public String deleteBilling(@PathVariable int billingid) {
        return service.deleteBilling((long) billingid);
    }
}