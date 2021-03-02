package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Transaction;
import java.util.List;

public interface TransactionService {
    void createTransfer(String fromAccountNumber, String toAccountNumber,
                        Double amount);

    List<Transaction> getAllByAccount(int page, int size, String accountNumber);
}
