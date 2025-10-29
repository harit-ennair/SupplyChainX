package org.example.supplychainx.dto.production;

import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private String name;
    private Integer productionTime;
    private Double cost;
    private Integer stock;
}
