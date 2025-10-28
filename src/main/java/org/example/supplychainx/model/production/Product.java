package org.example.supplychainx.model.production;

import jakarta.persistence.*;
import lombok.*;
import org.example.supplychainx.model.livraison.Order;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private Integer productionTime;
    private Double cost;
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<BillOfMaterial> billOfMaterials;

    @OneToMany(mappedBy = "product")
    private List<ProductionOrder> productionOrders;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;
}
