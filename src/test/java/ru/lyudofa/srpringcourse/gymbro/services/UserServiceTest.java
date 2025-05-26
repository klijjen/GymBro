package ru.lyudofa.srpringcourse.gymbro.services;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder; // Предполагается, что вы используете Spring Security
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Предполагается, что у вас есть класс User со следующими методами:
 * - getUsername()
 * - getEmail()
 * - getPasswordHash()
 * - setPasswordHash(String passwordHash)
 *
 * И интерфейс/класс UserRepository с методами:
 * - boolean existsByUsername(String username)
 * - boolean existsByEmail(String email)
 * - User save(User user)
 *
 * И интерфейс/класс PasswordEncoder с методом:
 * - String encode(CharSequence rawPassword)
 */

@ExtendWith(MockitoExtension.class)
class UserServiceTest { // Замените UserService на имя вашего сервисного класса

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService; // Замените YourUserService на имя вашего сервисного класса

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(); // Предполагается, что класс User существует
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPasswordHash("rawPassword123"); // Изначально это сырой пароль
    }

//    @Test
//    void registerUser_shouldReturnTrue_whenUserIsValidAndDoesNotExist() {
//        // Arrange
//        when(userRepository.existsByUsername("testuser")).thenReturn(false);
//        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
//        when(passwordEncoder.encode("rawPassword123")).thenReturn("encodedPassword123");
//        // Для userRepository.save(user) можно не указывать when().thenReturn(), если метод save возвращает void
//        // или если возвращаемое значение не используется в логике registerUser после сохранения.
//        // Если save возвращает сохраненного пользователя и это важно, можно добавить:
//        // when(userRepository.save(any(User.class))).thenReturn(testUser);
//
//        // Act
//        boolean result = userService.registerUser(testUser);
//
//        // Assert
//        assertTrue(result, "Регистрация должна быть успешной");
//        assertEquals("encodedPassword123", testUser.getPasswordHash(), "Пароль должен быть закодирован");
//        verify(userRepository).existsByUsername("testuser");
//        verify(userRepository).existsByEmail("test@example.com");
//        verify(passwordEncoder).encode("rawPassword123");
//        verify(userRepository).save(testUser);
//    }

//    @Test
//    void registerUser_shouldReturnFalse_whenUsernameAlreadyExists() {
//        // Arrange
//        when(userRepository.existsByUsername("testuser")).thenReturn(true);
//
//        // Act
//        boolean result = userService.registerUser(testUser);
//
//        // Assert
//        assertFalse(result, "Регистрация должна провалиться, если имя пользователя существует");
//        verify(userRepository).existsByUsername("testuser");
//        verify(userRepository, never()).existsByEmail(anyString());
//        verify(passwordEncoder, never()).encode(anyString());
//        verify(userRepository, never()).save(any(User.class));
//    }
//
//    @Test
//    void registerUser_shouldReturnFalse_whenEmailAlreadyExists() {
//        // Arrange
//        when(userRepository.existsByUsername("testuser")).thenReturn(false);
//        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);
//
//        // Act
//        boolean result = userService.registerUser(testUser);
//
//        // Assert
//        assertFalse(result, "Регистрация должна провалиться, если email существует");
//        verify(userRepository).existsByUsername("testuser");
//        verify(userRepository).existsByEmail("test@example.com");
//        verify(passwordEncoder, never()).encode(anyString());
//        verify(userRepository, never()).save(any(User.class));
//    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenUsernameIsNull() {
        // Arrange
        testUser.setUsername(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Username is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenUsernameIsBlank() {
        // Arrange
        testUser.setUsername("   ");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Username is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenEmailIsNull() {
        // Arrange
        testUser.setEmail(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Email is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenEmailIsBlank() {
        // Arrange
        testUser.setEmail("");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Email is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenPasswordHashIsNull() {
        // Arrange
        testUser.setPasswordHash(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Password is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldThrowIllegalArgumentException_whenPasswordHashIsBlank() {
        // Arrange
        testUser.setPasswordHash("  ");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(testUser);
        });
        assertEquals("Password is required", exception.getMessage());
        verifyNoInteractions(userRepository, passwordEncoder);
    }
}

/*
// Примерные классы User, UserRepository и YourUserService, если они вам нужны для контекста.
// Замените их на ваши реальные классы.

class User {
    private String username;
    private String email;
    private String passwordHash;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}

interface UserRepository { // Может быть классом, если вы не используете Spring Data JPA интерфейсы
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User save(User user); // Или void save(User user)
}

// Это класс, который содержит ваш метод registerUser
class YourUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public YourUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPasswordHash() == null || user.getPasswordHash().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
        return true;
    }
}
*/