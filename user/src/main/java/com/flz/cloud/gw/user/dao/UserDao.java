package com.flz.cloud.gw.user.dao;

import com.flz.cloud.gw.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    boolean existsByName(String name);

    Optional<User> findByNameAndPassword(String name, String password);
}
