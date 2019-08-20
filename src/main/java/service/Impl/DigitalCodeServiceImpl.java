package service.Impl;

import entity.DigitalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CodeRepository;
import service.DigitalCodeService;

import java.util.List;
import java.util.Optional;

@Service
public class DigitalCodeServiceImpl implements DigitalCodeService {
    private CodeRepository digitalCodeRepository;

    @Autowired
    public DigitalCodeServiceImpl(CodeRepository digitalCodeRepository) {
        this.digitalCodeRepository = digitalCodeRepository;
    }

    @Override
    public List<DigitalCode> getAll() {
        return digitalCodeRepository.findAll();
    }

    @Override
    public void create(DigitalCode digitalCode) {
        digitalCodeRepository.save(digitalCode);
    }

    @Override
    public Optional<DigitalCode> getCodeById(Long id) {
        return digitalCodeRepository.findById(id);
    }

    @Override
    public Optional<DigitalCode> getCodeByUserId(Long userId) {
        return digitalCodeRepository.getDigitalCodeByUserID(userId);
    }

    @Override
    public void remove(Long codeId) {
        digitalCodeRepository.deleteById(codeId);
    }
}
