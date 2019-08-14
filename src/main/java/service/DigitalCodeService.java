package service;

import entity.DigitalCode;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface DigitalCodeService {
    List<DigitalCode> getAll();
    void create(DigitalCode digitalCode);
    Optional<DigitalCode> getCodeById(Long id);
    Optional<DigitalCode> getCodeByUserId(Long userId);
    void remove(Long codeId);
}
