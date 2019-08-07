package controllers;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BasketService;
import service.ProductService;
import service.UserService;
import utils.PasswordSaltGenerator;

import java.util.Optional;

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

    @ModelAttribute("user")
    public User setUserToCurrentSession(User user) {
        return user;
    }

    @GetMapping("/")
    public String init() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @ModelAttribute("user") User user,
                        Model model) {
        String saltedPassword = "";
        User registeredUser = null;
        Optional<User> optionalUser = userService.getUserByLogin(login);
        if (optionalUser.isPresent()) {
            registeredUser = optionalUser.get();
            saltedPassword = PasswordSaltGenerator.saltPassword(password, registeredUser.getSalt());
        }
        if (registeredUser != null && registeredUser.getPassword().equals(saltedPassword)) {
            user.setId(registeredUser.getId());
            user.setEmail(registeredUser.getEmail());
            user.setAccessRole(registeredUser.getAccessRole());
            if ("admin".equals(user.getAccessRole())) {
                return "redirect:/admin/users";
            } else {
                return "redirect:/user/products";
            }
        } else {
            model.addAttribute("error", "No such user found");
            return "index";
        }
    }

    @GetMapping("/init")
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
