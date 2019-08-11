package service.Impl;

import dao.DigitalCodeDao;
import entity.DigitalCode;
import entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DigitalCodeService;

import java.util.Optional;

@Service
public class DigitalCodeServiceImpl implements DigitalCodeService {
    private  DigitalCodeDao digitalCodeDao;

    @Autowired
    public DigitalCodeServiceImpl(DigitalCodeDao digitalCodeDao){
        this.digitalCodeDao = digitalCodeDao;
    }

    @Override
    public void create(DigitalCode digitalCode) {
        digitalCodeDao.create(digitalCode);
    }

    @Override
    public Optional<DigitalCode> getById(Long codeID) {
        return digitalCodeDao.getById(codeID);
    }

    @Override
    public Optional<DigitalCode> getLastCode(User user) {
        return digitalCodeDao.getLastCode(user);
    }
}
