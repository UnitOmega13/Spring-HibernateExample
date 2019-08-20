package service;

import entity.OrderDetails;
import entity.User;

import java.util.Optional;

public interface OrderService {
    void addOrder (OrderDetails OrderDetails);
    Optional<OrderDetails> getById(Long orderID);
    Optional<OrderDetails> getUsersOrder(User user);
}
