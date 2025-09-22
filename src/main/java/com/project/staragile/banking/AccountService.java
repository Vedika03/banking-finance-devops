package com.project.staragile.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    private String accountNo;      // matches path variable

    private String accountName;
    private String policyType;     // renamed from accountType
    private double sumInsured;     // renamed from balance
    private LocalDate startDate;   // policy start date

    public Account() {}
