package com.example.electricitybill.Repository;

import com.example.electricitybill.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Optional<Customer> findByEmail(String email);


    Optional<Customer> findByPhone(String phone);


    List<Customer> findByNameContainingIgnoreCase(String name);
}
