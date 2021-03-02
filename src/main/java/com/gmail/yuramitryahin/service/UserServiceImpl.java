package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.User;
import com.gmail.yuramitryahin.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).get();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
