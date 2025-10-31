package org.example.supplychainx.controller.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplyOrderDTO;
import org.example.supplychainx.service.approvisionnement.SupplyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supply-orders")
@RequiredArgsConstructor
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    @PostMapping
    public SupplyOrderDTO create(@RequestBody SupplyOrderDTO dto) {
        return supplyOrderService.create(dto);
    }

    @PutMapping("/{id}")
    public SupplyOrderDTO update(@PathVariable Long id, @RequestBody SupplyOrderDTO dto) {
        return supplyOrderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        supplyOrderService.delete(id);
    }

    @GetMapping("/{id}")
    public SupplyOrderDTO getById(@PathVariable Long id) {
        return supplyOrderService.getById(id);
    }

    @GetMapping
    public List<SupplyOrderDTO> getAll() {
        return supplyOrderService.getAll();
    }
}
