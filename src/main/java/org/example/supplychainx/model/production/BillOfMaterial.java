package org.example.supplychainx.model.production;

import jakarta.persistence.*;
import lombok.*;
import org.example.supplychainx.model.approvisionnement.RawMaterial;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillOfMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBOM;
    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private RawMaterial material;
}
