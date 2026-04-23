package com.example.electricitybill.Controller;

import com.example.electricitybill.DTO.Billing;
import com.example.electricitybill.Service.Billingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class Billingcontroller {

    @Autowired
    private Billingservice service;


    @PostMapping("/generate")
    public Billing generateBill(@RequestParam int meterId,
                                @RequestParam int units) {
        return service.generateBill(meterId, units);
    }


    @GetMapping
    public List<Billing> getAllBills() {
        return service.getAllBills();
    }


    @GetMapping("/{id}")
    public Billing getBill(@PathVariable int id) {
        return service.getBillById(id);
    }


    @GetMapping("/unpaid")
    public List<Billing> getUnpaidBills() {
        return service.getUnpaidBills();
    }


    @GetMapping("/customer/{customerId}")
    public List<Billing> getBillsByCustomer(@PathVariable int customerId) {
        return service.getBillsByCustomer(customerId);
    }


    @PutMapping("/pay/{id}")
    public Billing payBill(@PathVariable int id) {
        return service.payBill(id);
    }


    @GetMapping("/report")
    public List<Billing> getMonthlyReport(@RequestParam int month,
                                          @RequestParam int year) {
        return service.getBillsByMonth(month, year);
    }

    @GetMapping("/revenue")
    public double getRevenue() {
        return service.getTotalRevenue();
    }
}