package service;

import entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    @Transactional
    List<User> getAllUsers();

    @Transactional
    Optional<User> getUserByLogin(String login);

    @Transactional
    Optional<User> getUserById(long id);

    @Transactional
    void updateUser(User user);

    @Transactional
    void addUser(User name);

    @Transactional
    void removeUser(Long id);
}
