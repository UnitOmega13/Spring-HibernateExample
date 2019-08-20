package controllers;

import entity.Basket;
import entity.DigitalCode;
import entity.OrderDetails;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BasketService;
import service.DigitalCodeService;
import service.MailService;
import service.OrderService;

import java.util.Optional;

@Controller
@RequestMapping("/user/order")
public class OrdersController {
    private MailService mailService;
    private OrderService orderService;
    private DigitalCodeService digitalCodeService;
    private BasketService basketService;

    @Autowired
    public OrdersController(MailService mailService, OrderService orderService,
                            DigitalCodeService digitalCodeService, BasketService basketService) {
        this.mailService = mailService;
        this.orderService = orderService;
        this.digitalCodeService = digitalCodeService;
        this.basketService = basketService;
    }

    @GetMapping
    public ModelAndView showPaymentPage() {
        return new ModelAndView("userOrder", "order", new OrderDetails());
    }

    @PostMapping
    public String createOrder(@ModelAttribute("order") OrderDetails orderDetails,
                              @SessionAttribute("user") User user) {
        digitalCodeService.create(new DigitalCode(user));
        DigitalCode digitalCode = null;
        Optional<DigitalCode> optionalCode = digitalCodeService.getLastCode(user);
        if (optionalCode.isPresent()) {
            digitalCode = optionalCode.get();
        }

        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getUserBasket(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }

        orderDetails.setUser(user);
        orderDetails.setBasket(basket);
        orderDetails.setDigitalCode(digitalCode);
        orderService.addOrder(orderDetails);
        new Thread(() -> mailService.sendConfirmCode(orderDetails)).start();
        return "redirect:/user/payment/confirm";
    }

    @GetMapping("/confirm")
    public String showConfirmOrderPage() {
        return "payment_confirm";
    }

    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam("confirm") String confirm,
                               @SessionAttribute("user") User user,
                               Model model) {
        Optional<OrderDetails> optionalOrderDetails = orderService.getUsersOrder(user);
        if (optionalOrderDetails.isPresent()) {
            OrderDetails orderDetails = optionalOrderDetails.get();
            if (orderDetails.getDigitalCode().getValue().equals(confirm)) {
                Basket basket = new Basket(orderDetails.getUser());
                basketService.createBasket(basket);
                model.addAttribute("message", "Success!");
            } else {
                model.addAttribute("message", "Error! Wrong digital code!");
            }
        }
        return "confirmed";
    }
}
