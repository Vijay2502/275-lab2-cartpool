package edu.sjsu.cmpe275.cartpool.service;

import edu.sjsu.cmpe275.cartpool.exceptions.StoreNotFoundException;
import edu.sjsu.cmpe275.cartpool.exceptions.UserNotFoundException;
import edu.sjsu.cmpe275.cartpool.pojos.Admin;
import edu.sjsu.cmpe275.cartpool.pojos.Product;
import edu.sjsu.cmpe275.cartpool.pojos.ProductId;
import edu.sjsu.cmpe275.cartpool.pojos.Store;
import edu.sjsu.cmpe275.cartpool.repository.AdminRepository;
import edu.sjsu.cmpe275.cartpool.repository.ProductRepository;
import edu.sjsu.cmpe275.cartpool.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    @Autowired
    AdminRepository<Admin> adminRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository<Store> storeRepository;

    @Override
    @Transactional
    public Product createProduct(Product product, Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Set<Product> deleteProduct(Long storeId, Long productId, Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException());
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException());
        System.out.println("Deleting product: " );
        productRepository.deleteById(new ProductId(storeId,productId));
        return store.getProducts();
    }

    @Override
    @Transactional
    public Set<Product> updateProduct(Product product, Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException());
        productRepository.save(product);
        return product.getStore().getProducts();

    }

    @Override
    @Transactional
    public Set<Product> searchProductByStoreId(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException());
        return store.getProducts();
    }

}