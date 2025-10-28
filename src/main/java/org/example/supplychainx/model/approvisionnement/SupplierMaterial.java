package org.example.supplychainx.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSP;
//    private Integer quantity;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private RawMaterial material;
}
