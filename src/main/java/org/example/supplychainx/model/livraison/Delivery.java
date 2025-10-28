package org.example.supplychainx.model.livraison;

import jakarta.persistence.*;
import lombok.*;
import org.example.supplychainx.model.production.DeliveryStatusEnum;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;
    private String vehicle;
    private String driver;
    private LocalDate deliveryDate;
    private Double cost;

    @Enumerated(EnumType.STRING)
    private DeliveryStatusEnum status;

    @OneToOne
    private Order order;
}
