package com.example.electricitybill.Repository;

import com.example.electricitybill.DTO.Billing;
import com.example.electricitybill.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Integer> {

    List<Billing> findByStatus(String status);

    List<Billing> findByMeterCustomer(Customer customer);

    @Query("SELECT b FROM Billing b WHERE MONTH(b.billingDate) = :month AND YEAR(b.billingDate) = :year")
    List<Billing> findByMonthAndYear(int month, int year);
    @Query("SELECT SUM(b.billAmount) FROM Billing b WHERE b.status = 'PAID'")
    Double getTotalRevenue();
}