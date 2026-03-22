package dev.talles.ecommerce.basketservice.controller;

import dev.talles.ecommerce.basketservice.controller.request.BasketRequest;
import dev.talles.ecommerce.basketservice.entity.Basket;
import dev.talles.ecommerce.basketservice.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService service;

    public BasketController(BasketService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createBasket(request));
    }
}
