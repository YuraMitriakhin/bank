package com.gmail.yuramitryahin.repository;

import com.gmail.yuramitryahin.entity.Account;
import com.gmail.yuramitryahin.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> getAllByUser(User user);
}
