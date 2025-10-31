package org.example.supplychainx.repository.production;

import org.example.supplychainx.model.production.ProductionOrder;
import org.example.supplychainx.model.production.ProductionStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {

    List<ProductionOrder> findByStatus(ProductionStatusEnum status);

    boolean existsByProductIdProduct(Long id);
}

