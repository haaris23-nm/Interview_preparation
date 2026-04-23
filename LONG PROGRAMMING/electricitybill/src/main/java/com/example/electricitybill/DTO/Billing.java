package com.example.electricitybill.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter

@Table(name = "Billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Billingid;
    @JoinColumn(name = "meter_id")
    @OneToOne
    private Meter meter;
    private int unitsConsumed;
    private double billAmount;
    private LocalDate billingDate;
    private LocalTime dueDate;
    private String status;

}
