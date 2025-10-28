package org.example.supplychainx.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;
    private String name;
    private String contact;
    private Double rating;
    private Integer leadTime;

    @OneToMany(mappedBy = "supplier")
    private List<SupplyOrder> orders;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierMaterial> supplierMaterials;
}
