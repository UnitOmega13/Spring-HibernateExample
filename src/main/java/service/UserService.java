package service;

import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    @Transactional
    void add(User user);

    @Transactional(readOnly = true)
    List<User> getAll();

    @Transactional
    Optional<User> getUserById(long userID);

    @Transactional
    Optional<User> getUserByEmail(String email);

    @Transactional
    Optional<User> getUserByLogin(String login);

    @Transactional
    void removeUser(long userID);

    @Transactional
    void update(User user);
}
