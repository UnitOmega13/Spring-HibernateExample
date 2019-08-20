package service.Impl;

import entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderDetailsRepository;
import service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderService {
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository){
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public void newOrder(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public Optional<OrderDetails> getOrderById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    @Override
    public Optional<OrderDetails> getOrderByUserId(Long userId) {
        return orderDetailsRepository.getOrderDetailsByUserId(userId);
    }

    @Override
    public void remove(Long id) {
        orderDetailsRepository.deleteById(id);
    }
}
