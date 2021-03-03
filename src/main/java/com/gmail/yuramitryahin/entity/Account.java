package com.gmail.yuramitryahin.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @ManyToOne
    private Currency currency;

    private BigDecimal balance;

    @Column(name = "active")
    private boolean isActive;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", accountNumber='" + accountNumber + '\''
                + ", currency=" + currency
                + ", balance=" + balance
                + ", isActive=" + isActive
                + '}';
    }
}
