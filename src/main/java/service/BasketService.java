package service;

import entity.Basket;
import entity.Product;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface BasketService {
    void createBasket(Basket basket);
    int size(Basket basket);
    Optional<Basket> getUserBasket(User user);
    void addProductToBasket(Basket basket, Product product);
}
