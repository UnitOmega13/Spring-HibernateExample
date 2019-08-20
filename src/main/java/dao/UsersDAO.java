package dao;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {

    void add(User user);
    List<User> getAll();
    Optional<User> getUserById(Long userId);
    void removeUser(Long userId);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByLogin(String login);
    void updateUser(User newUser);
}
