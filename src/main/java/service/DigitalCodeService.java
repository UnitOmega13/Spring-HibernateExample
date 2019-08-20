package service;

import entity.DigitalCode;
import entity.User;

import java.util.Optional;

public interface DigitalCodeService {
    void create(DigitalCode digitalCode);
    Optional<DigitalCode> getById(Long codeID);
    Optional<DigitalCode> getLastCode(User user);
}
