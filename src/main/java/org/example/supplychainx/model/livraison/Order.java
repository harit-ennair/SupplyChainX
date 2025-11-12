package org.example.supplychainx.model.livraison;

import jakarta.persistence.*;
import lombok.*;
import org.example.supplychainx.model.production.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @ManyToOne
    private Customer customer;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @ManyToOne
    private Product product;
}
