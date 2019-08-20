package service.Impl;

import dao.UsersDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

import java.util.List;
import java.util.Optional;

@Repository
public class UserServiceInpl implements UserService {
    @Autowired
    private UsersDAO userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public Optional<User> getUserById(long userID) {
        return userDao.getUserById(userID);
    }

    @Transactional
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    @Override
    public void removeUser(long userID) {
        userDao.removeUser(userID);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }
}
