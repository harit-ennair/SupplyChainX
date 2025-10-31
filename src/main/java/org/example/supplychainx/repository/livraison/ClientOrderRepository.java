package org.example.supplychainx.repository.livraison;

import org.example.supplychainx.model.livraison.Order;
import org.example.supplychainx.model.livraison.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatusEnum status);

    boolean existsByCustomerIdCustomerAndStatusIn(Long customerId, Collection<OrderStatusEnum> statuses);

}
