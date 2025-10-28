package org.example.supplychainx.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSP;
    private Integer quantity;

    @ManyToOne
    private  SupplyOrder supplyOrder;

    @ManyToOne
    private RawMaterial material;
}
