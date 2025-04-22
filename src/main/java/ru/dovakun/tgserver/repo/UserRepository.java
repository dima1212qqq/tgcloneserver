package ru.dovakun.tgserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dovakun.tgserver.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}

