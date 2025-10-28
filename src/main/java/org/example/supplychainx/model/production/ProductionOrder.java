package org.example.supplychainx.model.production;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductionStatusEnum status;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Product product;
}
