package org.example.supplychainx.service.livraison.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.livraison.ClientOrderDTO;
import org.example.supplychainx.mapper.livraison.ClientOrderMapper;
import org.example.supplychainx.model.livraison.Order;
import org.example.supplychainx.model.livraison.OrderStatusEnum;
import org.example.supplychainx.model.production.Product;
import org.example.supplychainx.repository.livraison.ClientOrderRepository;
import org.example.supplychainx.repository.production.ProductRepository;
import org.example.supplychainx.service.livraison.ClientOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {

    private final ClientOrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final ClientOrderMapper mapper;

    @Override
    public ClientOrderDTO create(ClientOrderDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        if (product.getStock() < dto.getQuantity()) {
            throw new RuntimeException("Stock insuffisant pour ce produit.");
        }

        product.setStock(product.getStock() - dto.getQuantity());
        productRepo.save(product);

        Order order = mapper.toEntity(dto);
        order.setStatus(OrderStatusEnum.EN_PREPARATION);
        return mapper.toDto(orderRepo.save(order));
    }

    @Override
    public ClientOrderDTO update(Long id, ClientOrderDTO dto) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        if (order.getStatus() != OrderStatusEnum.EN_PREPARATION)
            throw new RuntimeException("Impossible de modifier une commande déjà expédiée.");

        order.setStatus(dto.getStatus());
        return mapper.toDto(orderRepo.save(order));
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        if (order.getStatus() != OrderStatusEnum.EN_PREPARATION)
            throw new RuntimeException("Impossible d'annuler une commande déjà expédiée.");

        orderRepo.delete(order);
    }

    @Override
    public ClientOrderDTO getById(Long id) {
        return orderRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
    }

    @Override
    public List<ClientOrderDTO> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
