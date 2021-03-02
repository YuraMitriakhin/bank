package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.User;

public interface UserService {
    void add(User user);

    void update(User user);

    User getById(Long id);

    User getByPhoneNumber(String phoneNumber);

    void delete(User user);
}
