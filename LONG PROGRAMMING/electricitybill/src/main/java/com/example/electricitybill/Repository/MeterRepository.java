package com.example.electricitybill.Repository;

import com.example.electricitybill.DTO.Customer;
import com.example.electricitybill.DTO.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Integer> {

    Optional<Meter> findByMeterNumber(String meterNumber);

    List<Meter> findByCustomer(Customer customer);

    List<Meter> findByCustomerId(int customerId);
}
