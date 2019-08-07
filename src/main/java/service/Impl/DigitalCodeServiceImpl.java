package service.Impl;

import dao.DigitalCodeDao;
import entity.DigitalCode;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.DigitalCodeService;

import java.util.Optional;

public class DigitalCodeServiceImpl implements DigitalCodeService {
    @Autowired
    private  DigitalCodeDao digitalCodeDao;

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
