package service;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void add(User user);
    List<User> getAll();
    Optional<User> getUserById(long userID);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByLogin(String login);
    void removeUser(long userID);
    void update(User user);
}
