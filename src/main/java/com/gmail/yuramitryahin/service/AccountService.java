package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Account;
import com.gmail.yuramitryahin.entity.User;
import java.util.List;

public interface AccountService {
    Account save(Account account);

    Account getByAccountNumber(String accountNumber);

    List<Account> getByUser(User user);
}
