package org.example.supplychainx.dto.approvisionnement;

import lombok.Data;

@Data
public class RawMaterialDTO {
    private Long idMaterial;
    private String name;
    private Integer stock;
    private Integer stockMin;
    private String unit;
}
