package service.Impl;

import dao.OrderDetailsDAO;
import entity.OrderDetails;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;

import java.util.Optional;

@Repository
public class OrderDetailsServiceImpl implements OrderService {
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;

    @Transactional
    @Override
    public void addOrder(OrderDetails orderDetails) {
        orderDetailsDAO.addOrder(orderDetails);
    }

    @Override
    public Optional<OrderDetails> getById(Long orderID) {
        return orderDetailsDAO.getById(orderID);
    }

    @Override
    public Optional<OrderDetails> getUsersOrder(User user) {
        return orderDetailsDAO.getUsersOrder(user);
    }
}
