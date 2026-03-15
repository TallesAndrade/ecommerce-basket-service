package dev.talles.ecommerce.basketservice.service;

import dev.talles.ecommerce.basketservice.client.PlatziStoreClient;
import dev.talles.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    @Cacheable(value = "products")
    public List<PlatziProductResponse> findAll(){
        log.info("Find all products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product",key = "#id")
    public PlatziProductResponse findById(Long id){
        log.info("Find product by id: {}", id);
        return platziStoreClient.getProductById(id);
    }
}
