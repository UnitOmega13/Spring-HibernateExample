package service;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BasketService {
    @Transactional(readOnly = true)
    List<Basket> getAll();

    @Transactional
    void createBasket(Basket basket);

    @Transactional
    Optional<Basket> getBasketById(Long basketId);

    @Transactional
    Optional<Basket> getBasketByUser(Long userId);

    @Transactional
    void clearBasket(Long basketId);

    @Transactional
    void addProduct(Basket basket, Product product);

    @Transactional
    void removeProduct(Basket basket, Product product);
}
