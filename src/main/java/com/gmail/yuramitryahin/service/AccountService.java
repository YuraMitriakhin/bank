package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Account;
import java.util.List;

public interface AccountService {
    Account create(Account account);

    Account getByAccountNumber(String accountNumber);

    List<Account> getByUserPhoneNumber(String phoneNumber);

    Double getBalance(String accountNumber);

    void block(String accountNumber);
}
