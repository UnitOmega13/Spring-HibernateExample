package service;

import entity.OrderDetails;
import org.springframework.transaction.annotation.Transactional;

public interface MailService {
    void sendConfirmCode(OrderDetails orderDetails);
}
