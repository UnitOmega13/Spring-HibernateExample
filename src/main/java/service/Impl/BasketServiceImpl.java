package service.Impl;

import dao.BasketDAO;
import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.BasketService;

import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketDAO basketDAO;

    @Autowired
    public BasketServiceImpl(BasketDAO basketDao) {
        this.basketDAO = basketDao;
    }

    @Transactional
    @Override
    public void createBasket(Basket basket) {
        basketDAO.createBasket(basket);
    }

    @Transactional
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
