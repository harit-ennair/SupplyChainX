package org.example.supplychainx.dto.approvisionnement;

import org.example.supplychainx.model.approvisionnement.SupplyOrderStatusEnum;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SupplyOrderDTO {
    private Long idOrder;
    private LocalDate orderDate;
    private SupplyOrderStatusEnum status;
    private Long supplierId;
    private List<SupplyMaterialDTO> materials;
}
