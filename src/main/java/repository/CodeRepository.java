package repository;

import entity.DigitalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<DigitalCode, Long> {
    Optional<DigitalCode> getDigitalCodeByUserID(Long userId);
}
