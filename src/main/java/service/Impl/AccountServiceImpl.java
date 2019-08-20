package service.Impl;

import entity.User;
import repository.UserRepository;
import service.AccountService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.getOne(id));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(User name) {
        userRepository.save(name);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.existsById(id);
    }
}
