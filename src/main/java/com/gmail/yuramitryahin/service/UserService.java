package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.User;

public interface UserService {
    User add(User user);

    User update(User user);

    User getById(Long id);

    User getByPhoneNumber(String phoneNumber);

    void delete(Long id);
}
