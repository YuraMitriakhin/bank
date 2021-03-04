package com.gmail.yuramitryahin.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_account")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account")
    private Account toAccount;

    @Column(name = "date")
    private LocalDateTime dateTime;

    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    public enum TransactionType {
        INCOMING,
        OUTCOMING
    }
}
