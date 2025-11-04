package org.example.supplychainx.dto.approvisionnement;

import lombok.Data;

import java.util.List;

@Data
public class SupplierDTO {
    private Long idSupplier;
    private String name;
    private String contact;
    private Double rating;
    private Integer leadTime;
    private List<Long> materialIds;
}
