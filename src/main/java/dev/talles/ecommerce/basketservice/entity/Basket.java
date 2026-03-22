package dev.talles.ecommerce.basketservice.entity;

import dev.talles.ecommerce.basketservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collation = "basket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    @Id
    private String id;

    private Long clientId;

    private BigDecimal totalPrice;

    private List<Product> products;

    private Status status;

    public void calculateTotalPrice() {
        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
