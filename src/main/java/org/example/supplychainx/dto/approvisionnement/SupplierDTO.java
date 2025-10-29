package org.example.supplychainx.dto.approvisionnement;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long idSupplier;
    private String name;
    private String contact;
    private Double rating;
    private Integer leadTime;
}
