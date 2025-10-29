package org.example.supplychainx.dto.production;

import org.example.supplychainx.model.production.ProductionStatusEnum;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductionOrderDTO {
    private Long idOrder;
    private Long productId;
    private Integer quantity;
    private ProductionStatusEnum status;
    private LocalDate startDate;
    private LocalDate endDate;
}
