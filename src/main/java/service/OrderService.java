package service;

import entity.OrderDetails;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDetails> getAll();
    void newOrder(OrderDetails orderDetails);
    Optional<OrderDetails> getOrderById(Long id);
    Optional<OrderDetails> getOrderByUserId(Long userId);
    void remove(Long id);
}
