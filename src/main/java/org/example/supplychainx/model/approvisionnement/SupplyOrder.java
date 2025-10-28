package org.example.supplychainx.model.approvisionnement;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private SupplyOrderStatusEnum status;

    @ManyToOne
    private Supplier supplier;
}
