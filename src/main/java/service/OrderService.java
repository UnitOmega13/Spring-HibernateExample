package service;

import entity.OrderDetails;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    @Transactional(readOnly = true)
    List<OrderDetails> getAll();

    @Transactional
    void newOrder(OrderDetails orderDetails);

    @Transactional
    Optional<OrderDetails> getOrderById(Long id);

    @Transactional
    Optional<OrderDetails> getOrderByUserId(Long userId);

    @Transactional
    void remove(Long id);
}
