package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Account;
import com.gmail.yuramitryahin.entity.User;
import com.gmail.yuramitryahin.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }

    @Override
    public List<Account> getByUserPhoneNumber(String phoneNumber) {
        User user = userService.getByPhoneNumber(phoneNumber);
        return accountRepository.getAllByUser(user);
    }

    @Override
    public Double getBalance(String accountNumber) {
        return accountRepository.findById(accountNumber).get().getBalance();
    }

    @Override
    public void block(String accountNumber) {
        Account account = accountRepository.findById(accountNumber).get();
        account.setActive(false);
        accountRepository.save(account);
    }
}
