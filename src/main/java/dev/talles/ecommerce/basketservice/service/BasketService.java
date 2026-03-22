package dev.talles.ecommerce.basketservice.service;

import dev.talles.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.talles.ecommerce.basketservice.controller.request.BasketRequest;
import dev.talles.ecommerce.basketservice.entity.Basket;
import dev.talles.ecommerce.basketservice.entity.Product;
import dev.talles.ecommerce.basketservice.enums.Status;
import dev.talles.ecommerce.basketservice.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository,ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    public Basket createBasket(BasketRequest request){

        basketRepository.findByClientIdAndStatus(request.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalArgumentException("Client already has an open basket");
                });

        List<Product> products = request.products().stream().map
                (productRequest -> {
                    PlatziProductResponse platziProduct = productService.findById(productRequest.id());
                Product product = new Product();
                product.setId(platziProduct.id());
                product.setTitle(platziProduct.title());
                product.setPrice(platziProduct.price());
                product.setQuantity(productRequest.quantity());
                return product;
                })
                .toList();


        Basket basket = new Basket();
        basket.setProducts(products);

        return basketRepository.save(basket);
    }
}
