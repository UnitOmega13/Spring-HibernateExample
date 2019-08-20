package service;

import entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    @Transactional
    void add(Product product);

    @Transactional(readOnly = true)
    List<Product> getAll();

    @Transactional
    Optional<Product> getProduct(long productID);

    @Transactional
    void removeProduct(long productID);

    @Transactional
    void updateProduct(Product product);
}
