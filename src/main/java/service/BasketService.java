package service;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BasketService {
    List<Basket> getAll();
    void createBasket(Basket basket);
    Optional<Basket> getBasketById(Long basketId);
    Optional<Basket> getBasketByUser(Long userId);
    void clearBasket(Long basketId);
    void addProduct(Basket basket, Product product);
    void removeProduct(Basket basket, Product product);
}
