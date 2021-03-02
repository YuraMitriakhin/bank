package com.gmail.yuramitryahin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Account {
    @Id
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @ManyToOne
    private Currency currency;

    private Double balance;

    @Column(name = "active")
    private boolean isActive;

    @ManyToOne
    private User user;
}
