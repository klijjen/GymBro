package ru.lyudofa.srpringcourse.gymbro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lyudofa.srpringcourse.gymbro.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Поиск по части username (без учета регистра)
    List<User> findByUsernameContainingIgnoreCase(String usernamePart);

    // Найти пользователей, зарегистрированных между датами
    List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // Найти пользователей по нескольким условиям
    List<User> findByUsernameContainingAndEmailContainingAndCreatedAtAfter(
            String usernamePart,
            String emailPart,
            LocalDateTime date
    );
}
