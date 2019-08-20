package service.Impl;

import dao.ProductDAO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.ProductService;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Transactional
    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Transactional
    @Override
    public Optional<Product> getProduct(long productID) {
        return productDAO.getProduct(productID);
    }

    @Transactional
    @Override
    public void removeProduct(long productID) {
        productDAO.removeProduct(productID);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
}
