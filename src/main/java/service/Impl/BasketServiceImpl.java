package service.Impl;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BasketRepository;
import service.BasketService;

import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    @Override
    public List<Basket> getAll() {
        return basketRepository.findAll();
    }

    @Transactional
    @Override
    public void createBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Transactional
    @Override
    public Optional<Basket> getBasketById(Long id) {
        return basketRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Basket> getBasketByUser(Long userId) {
        return basketRepository.getBasketByUserId(userId);
    }

    @Transactional
    @Override
    public void clearBasket(Long basketId) {
        if (getBasketById(basketId).isPresent()) {
            Basket basket = getBasketById(basketId).get();
            basket.getProducts().clear();
            basketRepository.save(basket);
        }
    }

    @Transactional
    @Override
    public void addProduct(Basket basket, Product product) {
        basket.getProducts().add(product);
        basketRepository.save(basket);
    }

    @Transactional
    @Override
    public void removeProduct(Basket basket, Product product) {
        basket.getProducts().remove(product);
        basketRepository.save(basket);
    }
}
