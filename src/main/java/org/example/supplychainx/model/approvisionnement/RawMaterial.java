package org.example.supplychainx.model.approvisionnement;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "materials")
    private List<Supplier> suppliers;
}
