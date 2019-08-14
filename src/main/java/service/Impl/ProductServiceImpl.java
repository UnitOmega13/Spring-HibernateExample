package service.Impl;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Product> getProduct(long productID) {
        return productRepository.findById(productID);
    }

    @Transactional
    @Override
    public void removeProduct(long productID) {
        productRepository.deleteById(productID);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
