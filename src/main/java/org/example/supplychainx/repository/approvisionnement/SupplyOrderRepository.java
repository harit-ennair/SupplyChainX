package org.example.supplychainx.repository.approvisionnement;

import org.example.supplychainx.model.approvisionnement.SupplyOrder;
import org.example.supplychainx.model.approvisionnement.SupplyOrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

    List<SupplyOrder> findByStatus(SupplyOrderStatusEnum status);

    List<SupplyOrder> findBySupplierIdSupplier(Long supplierId);
}
