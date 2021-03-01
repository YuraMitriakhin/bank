package com.gmail.yuramitryahin.repository;

import com.gmail.yuramitryahin.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<User> findById(Long id);

    @Query("select u from User u join fetch u.roles WHERE u.phoneNumber = :number")
    Optional<User> findByPhoneNumber(@Param("number") String phoneNumber);
}
