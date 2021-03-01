package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.User;
import java.util.Optional;

public interface UserService {
    void add(User user);

    void update(User user);

    Optional<User> getById(Long id);

    Optional<User> getByPhoneNumber(String phoneNumber);

    void delete(User user);
}
