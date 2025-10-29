package org.example.supplychainx.dto.approvisionnement;

import lombok.Data;

@Data
public class SupplyMaterialDTO {
    private Long idSM;
    private Long materialId;
    private Integer quantity;
}
