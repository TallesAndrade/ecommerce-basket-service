package dev.talles.ecommerce.basketservice.repository;

import dev.talles.ecommerce.basketservice.entity.Basket;
import dev.talles.ecommerce.basketservice.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket,String> {

    Optional<Basket> findByClientIdAndStatus(Long clientId, Status status);
}
