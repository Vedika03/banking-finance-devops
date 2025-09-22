package com.project.staragile.banking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    public Account(String accountNo, String accountName, String policyType, double sumInsured, LocalDate startDate) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.policyType = policyType;
        this.sumInsured = sumInsured;
        this.startDate = startDate;
    }

    // Getters & Setters
    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getPolicyType() { return policyType; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }

    public double getSumInsured() { return sumInsured; }
    public void setSumInsured(double sumInsured) { this.sumInsured = sumInsured; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
