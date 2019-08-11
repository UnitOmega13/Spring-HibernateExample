package controllers;

import entity.Basket;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import service.BasketService;
import service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/user/products")
public class BasketController {
    private BasketService basketService;
    private ProductService productService;

    @Autowired
    public BasketController(BasketService basketService,
                            ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(@AuthenticationPrincipal User user,
                                      Model model) {
        Optional<Basket> optionalBasket = basketService.getUserBasket(user);
        optionalBasket.ifPresent(basket ->
                model.addAttribute("size", basketService.size(basket)));
        model.addAttribute("productList", productService.getAll());
        return "user_products";
    }

    @GetMapping("/buy/{id}")
    public String showBasketSize(@PathVariable("id") Long productID,
                                 @AuthenticationPrincipal User user) {
        Product product = null;
        Optional<Product> optionalProduct = productService.getProduct(productID);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }
        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getUserBasket(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }
        basketService.addProductToBasket(basket, product);
        return "redirect:/user/products";
    }
}
