package org.example.supplychainx.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterial;
    private String name;
    private Integer stock;
    private Integer stockMin;
    private String unit;

    @OneToMany(mappedBy = "material")
    private List<SupplierMaterial> supplierMaterials;
}
