package org.example.supplychainx.repository.livraison;

import org.example.supplychainx.model.livraison.Delivery;
import org.example.supplychainx.model.production.DeliveryStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByStatus(DeliveryStatusEnum status);
}
