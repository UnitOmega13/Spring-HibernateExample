package controllers;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "products";
    }

    @GetMapping("/add")
    public ModelAndView showAddProductPage() {
        return new ModelAndView("newProduct", "product", new Product());
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.add(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showChangeProductPage(@PathVariable Long productID, Model model) {
        Optional<Product> optionalProduct = productService.getProduct(productID);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
        }
        return "edit_product";
    }

    @PostMapping("/edit")
    private String editProduct(@RequestParam("edit") Long productId,
                               @RequestParam("product") String productName,
                               @RequestParam("description") String description,
                               @RequestParam("price") Double price) {
        Product product = productService.getProduct(productId).get();
        product.setName(productName);
        product.setDescription(description);
        product.setPrice(price);
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long productID) {
        productService.removeProduct(productID);
        return "redirect:/admin/products";
    }
}
