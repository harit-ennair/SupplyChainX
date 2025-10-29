package org.example.supplychainx.dto.livraison;

import org.example.supplychainx.model.livraison.OrderStatusEnum;
import lombok.Data;

@Data
public class ClientOrderDTO {
    private Long idOrder;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private OrderStatusEnum status;
}
