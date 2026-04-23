package com.example.electricitybill.Service;

import com.example.electricitybill.DTO.Billing;
import com.example.electricitybill.DTO.Customer;
import com.example.electricitybill.DTO.Meter;
import com.example.electricitybill.Repository.BillingRepository;
import com.example.electricitybill.Repository.CustomerRepository;
import com.example.electricitybill.Repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class Billingservice {

    @Autowired
    private BillingRepository billingRepo;

    @Autowired
    private MeterRepository meterRepo;

    @Autowired
    private CustomerRepository customerRepo;


    public Billing generateBill(int meterId, int units) {

        if (units < 0) {
            throw new IllegalArgumentException("Units cannot be negative");
        }

        Meter meter = meterRepo.findById(meterId)
                .orElseThrow(() -> new RuntimeException("Meter not found"));

        double amount = calculateBill(units);

        Billing bill = new Billing();
        bill.setMeter(meter);
        bill.setUnitsConsumed(units);
        bill.setBillAmount(amount);
        bill.setBillingDate(LocalDate.now());


        bill.setDueDate(LocalTime.from(LocalDate.now().plusDays(15)));

        bill.setStatus("UNPAID");

        return billingRepo.save(bill);
    }


    public double calculateBill(int units) {

        if (units <= 100) return units * 1.5;
        else if (units <= 300) return units * 2.5;
        else return units * 4.0;
    }


    public List<Billing> getAllBills() {
        return billingRepo.findAll();
    }


    public Billing getBillById(int id) {
        return billingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }


    public List<Billing> getUnpaidBills() {
        return billingRepo.findByStatus("UNPAID");
    }


    public List<Billing> getBillsByCustomer(int customerId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return billingRepo.findByMeterCustomer(customer);
    }


    public Billing payBill(int billId) {

        Billing bill = getBillById(billId);

        if (bill.getStatus().equalsIgnoreCase("PAID")) {
            throw new RuntimeException("Bill already paid");
        }

        double lateFee = calculateLateFee(bill);
        bill.setBillAmount(bill.getBillAmount() + lateFee);

        bill.setStatus("PAID");

        return billingRepo.save(bill);
    }


    public double calculateLateFee(Billing bill) {


        if (LocalDate.now().isAfter(ChronoLocalDate.from(bill.getDueDate()))) {
            return 50.0;
        }
        return 0;
    }


    public List<Billing> getBillsByMonth(int month, int year) {

        return billingRepo.findByMonthAndYear(month, year);
    }


    public double getTotalRevenue() {
        Double revenue = billingRepo.getTotalRevenue();
        return (revenue != null) ? revenue : 0.0;
    }
}