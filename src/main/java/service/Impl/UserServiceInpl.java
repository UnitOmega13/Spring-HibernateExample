package service.Impl;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceInpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceInpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<User> getUserById(long userID) {
        return userRepository.findById(userID);
    }

    @Transactional
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public void removeUser(long userID) {
        userRepository.deleteById(userID);
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
