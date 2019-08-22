package dao;

import entity.DigitalCode;
import entity.User;

import java.util.Optional;

public interface DigitalCodeDao {
    void create(DigitalCode digitalCode);
    Optional<DigitalCode> getById(Long id);
    Optional<DigitalCode> getLastCode(User user);
}
