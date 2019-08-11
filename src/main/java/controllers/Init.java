package controllers;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BasketService;
import service.ProductService;
import service.UserService;

@Controller
@SessionAttributes("user")
public class Init {
    private UserService userService;
    private BasketService basketService;
    private ProductService productService;

    @Autowired
    public Init(UserService userService,
                          BasketService basketService,
                          ProductService productService) {
        this.userService = userService;
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login";
        } else if ("ROLE_ADMIN".equals(user.getAccessRole())) {
            return "redirect:/admin/user";
        } else {
            return "redirect:/user/product";
        }
    }

    @GetMapping("/registration")
    public String addUser() {
        User admin = new User(1 ,"Max", "Hruslov", "example@example.com", "Admin");
        User admin2 = new User(2 ,"Maxim", "Hruslov", "example@example.com", "Admin");
        User user = new User(3, "Test", "Last_test", "test@example.com", "User");
        userService.add(admin);
        userService.add(user);
        Basket basket = new Basket(user);
        basketService.createBasket(basket);
        Product product = new Product(1L,"Phone", "New_v2", 159.99);
        productService.add(product);
        Product product2 = new Product(2L,"Phone", "Old_v1", 109.99);
        productService.add(product2);
        Product product3 = new Product(3L,"Headphones", "Wi-fi headphones", 29.99);
        productService.add(product3);
        Product product4 = new Product(3L,"Headphones", "Bluetooth headphones", 24.99);
        productService.add(product4);
        return "index";
    }
}
