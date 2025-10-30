package org.example.supplychainx.service.livraison.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.livraison.DeliveryDTO;
import org.example.supplychainx.mapper.livraison.DeliveryMapper;
import org.example.supplychainx.model.livraison.Delivery;
import org.example.supplychainx.model.livraison.Order;
import org.example.supplychainx.model.livraison.OrderStatusEnum;
import org.example.supplychainx.model.production.DeliveryStatusEnum;
import org.example.supplychainx.repository.livraison.ClientOrderRepository;
import org.example.supplychainx.repository.livraison.DeliveryRepository;
import org.example.supplychainx.service.livraison.DeliveryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepo;
    private final ClientOrderRepository orderRepo;
    private final DeliveryMapper mapper;

    @Override
    public DeliveryDTO create(DeliveryDTO dto) {
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        if (order.getStatus() != OrderStatusEnum.EN_ROUTE && order.getStatus() != OrderStatusEnum.EN_PREPARATION) {
            throw new RuntimeException("Commande non prête pour la livraison.");
        }

        Delivery delivery = mapper.toEntity(dto);
        delivery.setStatus(DeliveryStatusEnum.PLANIFIEE);
        delivery.setDeliveryDate(LocalDate.now().plusDays(2));
        return mapper.toDto(deliveryRepo.save(delivery));
    }

    @Override
    public DeliveryDTO update(Long id, DeliveryDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DeliveryDTO getById(Long id) {
        return null;
    }

    @Override
    public List<DeliveryDTO> getAll() {
        return List.of();
    }
}
