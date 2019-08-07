package service.Impl;

import dao.BasketDAO;
import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.BasketService;

import java.util.List;
import java.util.Optional;

@Repository
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketDAO basketDAO;

    @Transactional
    @Override
    public void createBasket(Basket basket) {
        basketDAO.createBasket(basket);
    }

    @Override
    public int size(Basket basket) {
        return basketDAO.size(basket);
    }

    @Transactional
    @Override
    public Optional<Basket> getUserBasket(User user) {
        return basketDAO.getUserBasket(user);
    }

    @Transactional
    @Override
    public void addProductToBasket(Basket basket, Product product) {
        basketDAO.addProductToBasket(basket, product);
    }
}
