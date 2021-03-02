package com.gmail.yuramitryahin.repository;

import com.gmail.yuramitryahin.entity.Transaction;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.fromAccount fAc JOIN FETCH t.toAccount "
            + "WHERE fAc.accountNumber = ?1")
    List<Transaction> getAllByAccountNumber(String accountNumber, PageRequest pageable);
}
