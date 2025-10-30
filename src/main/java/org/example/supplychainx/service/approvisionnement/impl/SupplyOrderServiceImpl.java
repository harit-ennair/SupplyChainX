package org.example.supplychainx.service.approvisionnement.impl;

import lombok.RequiredArgsConstructor;
import org.example.supplychainx.dto.approvisionnement.SupplyOrderDTO;
import org.example.supplychainx.mapper.approvisionnement.SupplyOrderMapper;
import org.example.supplychainx.model.approvisionnement.SupplyOrder;
import org.example.supplychainx.model.approvisionnement.SupplyOrderStatusEnum;
import org.example.supplychainx.repository.approvisionnement.SupplyOrderRepository;
import org.example.supplychainx.service.approvisionnement.SupplyOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyOrderServiceImpl implements SupplyOrderService {

    private final SupplyOrderRepository repo;
    private final SupplyOrderMapper mapper;

    @Override
    public SupplyOrderDTO create(SupplyOrderDTO dto) {
        SupplyOrder order = mapper.toEntity(dto);
        order.setStatus(SupplyOrderStatusEnum.EN_ATTENTE);
        order.setOrderDate(LocalDate.now());
        return mapper.toDto(repo.save(order));
    }

    @Override
    public SupplyOrderDTO update(Long id, SupplyOrderDTO dto) {
        SupplyOrder order = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        if (order.getStatus() == SupplyOrderStatusEnum.RECUE)
            throw new RuntimeException("Impossible de modifier une commande déjà reçue.");

        order.setStatus(dto.getStatus());
        return mapper.toDto(repo.save(order));
    }

    @Override
    public void delete(Long id) {
        SupplyOrder order = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        if (order.getStatus() == SupplyOrderStatusEnum.RECUE)
            throw new RuntimeException("Impossible de supprimer une commande déjà reçue.");

        repo.delete(order);
    }

    @Override
    public SupplyOrderDTO getById(Long id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
    }

    @Override
    public List<SupplyOrderDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
