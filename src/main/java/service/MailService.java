package service;

import entity.OrderDetails;
import org.springframework.transaction.annotation.Transactional;

public interface MailService {
    @Transactional
    void sendConfirmCode(OrderDetails orderDetails);
}
