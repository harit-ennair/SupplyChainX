package org.example.supplychainx.dto.livraison;

import lombok.Data;
import org.example.supplychainx.model.production.DeliveryStatusEnum;

import java.time.LocalDate;

@Data
public class DeliveryDTO {
    private Long idDelivery;
    private Long orderId;
    private String vehicle;
    private String driver;
    private LocalDate deliveryDate;
    private Double cost;
    private DeliveryStatusEnum status;
}
