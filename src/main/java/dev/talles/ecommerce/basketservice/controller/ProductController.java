package dev.talles.ecommerce.basketservice.controller;

import dev.talles.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.talles.ecommerce.basketservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<PlatziProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<PlatziProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
