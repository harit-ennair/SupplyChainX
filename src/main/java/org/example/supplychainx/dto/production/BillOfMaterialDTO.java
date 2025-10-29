package org.example.supplychainx.dto.production;

import lombok.Data;

@Data
public class BillOfMaterialDTO {
    private Long idBOM;
    private Long materialId;
    private Integer quantity;
}
