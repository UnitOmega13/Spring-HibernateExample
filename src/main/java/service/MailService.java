package service;

import entity.OrderDetails;

public interface MailService {
    void sendConfirmCode(OrderDetails orderDetails);
}
