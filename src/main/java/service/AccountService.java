package service;

import entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<User> getAllUsers();
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(long id);
    void updateUser(User user);
    void addUser(User name);
    void removeUser(Long id);
}
