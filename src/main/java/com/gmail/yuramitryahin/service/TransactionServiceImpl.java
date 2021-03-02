package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Account;
import com.gmail.yuramitryahin.entity.Transaction;
import com.gmail.yuramitryahin.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CurrencyTranslation currencyTranslation;

    @Override
    @Transactional
    public void createTransfer(String fromAccountNumber, String toAccountNumber, Double amount) {
        Account fromAccount = accountService.getByAccountNumber(fromAccountNumber);
        Account toAccount = accountService.getByAccountNumber(toAccountNumber);
        Transaction transactionFromAccount = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .dateTime(LocalDateTime.now())
                .amount(amount)
                .build();
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountService.create(fromAccount);
        transactionRepository.save(transactionFromAccount);

        Double currencyAmount = currencyTranslation.convert(fromAccount.getCurrency()
                .getCurrencyType(), toAccount.getCurrency().getCurrencyType(), amount);
        Transaction transactionToAccount = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .dateTime(LocalDateTime.now())
                .amount(currencyAmount)
                .build();
        toAccount.setBalance(toAccount.getBalance() + currencyAmount);
        accountService.create(toAccount);
        transactionRepository.save(transactionToAccount);
    }

    @Override
    public List<Transaction> getAllByAccount(int page, int size, String accountNumber) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return transactionRepository.getAllByAccountNumber(accountNumber, pageable);
    }
}
