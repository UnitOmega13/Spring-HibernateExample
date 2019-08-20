package service.Impl;

import dao.UsersDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UsersDAO usersDao;

    @Autowired
    public UserDetailsServiceImpl(UsersDAO userDao){
        this.usersDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersDao.getUserByLogin(login);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
