package org.example.supplychainx.dto.livraison;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long idCustomer;
    private String name;
    private String address;
    private String city;
}
