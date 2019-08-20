package service;

import entity.DigitalCode;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DigitalCodeService {
    @Transactional(readOnly = true)
    List<DigitalCode> getAll();

    @Transactional
    void create(DigitalCode digitalCode);

    @Transactional
    Optional<DigitalCode> getCodeById(Long id);

    @Transactional
    Optional<DigitalCode> getCodeByUserId(Long userId);

    @Transactional
    void remove(Long codeId);
}
