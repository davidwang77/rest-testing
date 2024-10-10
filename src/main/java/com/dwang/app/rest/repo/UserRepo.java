package com.dwang.app.rest.repo;

import com.dwang.app.rest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByFirstName(String firstName);
}
