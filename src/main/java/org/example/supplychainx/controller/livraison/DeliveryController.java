package org.example.supplychainx.controller.livraison;

import org.example.supplychainx.dto.livraison.DeliveryDTO;
import org.example.supplychainx.service.livraison.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public DeliveryDTO create(@RequestBody DeliveryDTO dto) {
        return deliveryService.create(dto);
    }

    @PutMapping("/{id}")
    public DeliveryDTO update(@PathVariable Long id, @RequestBody DeliveryDTO dto) {
        return deliveryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deliveryService.delete(id);
    }

    @GetMapping("/{id}")
    public DeliveryDTO getById(@PathVariable Long id) {
        return deliveryService.getById(id);
    }

    @GetMapping
    public List<DeliveryDTO> getAll() {
        return deliveryService.getAll();
    }
}
