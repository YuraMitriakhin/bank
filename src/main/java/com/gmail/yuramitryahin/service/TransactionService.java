package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void createTransfer(String fromAccountNumber, String toAccountNumber,
                        BigDecimal amount);

    List<Transaction> getAllByAccount(int page, int size, String accountNumber);
}
