package org.example.supplychainx.model.livraison;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String name;
    private String email;
    private String address;
    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
