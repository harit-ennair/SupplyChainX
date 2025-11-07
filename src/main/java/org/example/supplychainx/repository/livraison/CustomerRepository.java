package org.example.supplychainx.repository.livraison;

import org.example.supplychainx.model.livraison.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByNameContainingIgnoreCase(String name);

    boolean existsByEmailIgnoreCase(String name);
}
