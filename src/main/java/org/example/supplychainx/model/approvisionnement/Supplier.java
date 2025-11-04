package org.example.supplychainx.model.approvisionnement;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany
    @JoinTable(
        name = "supplier_material",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private List<RawMaterial> materials;
}
