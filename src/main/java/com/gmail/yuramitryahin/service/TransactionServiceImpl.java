package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Account;
import com.gmail.yuramitryahin.entity.Transaction;
import com.gmail.yuramitryahin.repository.TransactionRepository;
import java.math.BigDecimal;
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
    private final CurrencyExchangeService currencyTranslation;

    @Override
    @Transactional
    public void createTransfer(String fromAccountNumber, String toAccountNumber,
                               BigDecimal amount) {
        Account fromAccount = accountService.getByAccountNumber(fromAccountNumber);
        Account toAccount = accountService.getByAccountNumber(toAccountNumber);
        Transaction transactionFromAccount = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .dateTime(LocalDateTime.now())
                .amount(amount)
                .type(Transaction.TransactionType.OUTCOMING)
                .build();
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountService.save(fromAccount);
        transactionRepository.save(transactionFromAccount);

        Number currencyAmount = currencyTranslation.convert(fromAccount.getCurrency()
                .getCurrencyType(), toAccount.getCurrency().getCurrencyType(), amount);
        Transaction transactionToAccount = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .dateTime(LocalDateTime.now())
                .amount(BigDecimal.valueOf(currencyAmount.doubleValue()))
                .type(Transaction.TransactionType.INCOMING)
                .build();
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal
                .valueOf(currencyAmount.doubleValue())));
        accountService.save(toAccount);
        transactionRepository.save(transactionToAccount);
    }

    @Override
    public List<Transaction> getAllByAccount(int page, int size, String accountNumber) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return transactionRepository.getAllByAccountNumber(accountNumber, pageable);
    }
}
