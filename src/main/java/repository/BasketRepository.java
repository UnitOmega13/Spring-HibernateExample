package repository;

import entity.Basket;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("SELECT basket FROM Basket basket WHERE basket.user = :user AND basket.available= 'true'")
    Optional<Basket> getBasketByUserId(Long userId);
}
