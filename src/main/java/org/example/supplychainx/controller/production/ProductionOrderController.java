package org.example.supplychainx.controller.production;

import org.example.supplychainx.dto.production.ProductionOrderDTO;
import org.example.supplychainx.service.production.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/production-orders")
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    @PostMapping
    public ProductionOrderDTO create(@RequestBody ProductionOrderDTO dto) {

        return productionOrderService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductionOrderDTO update(@PathVariable Long id, @RequestBody ProductionOrderDTO dto) {
        return productionOrderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productionOrderService.delete(id);
    }

    @GetMapping("/{id}")
    public ProductionOrderDTO getById(@PathVariable Long id) {
        return productionOrderService.getById(id);
    }

    @GetMapping
    public List<ProductionOrderDTO> getAll() {
        return productionOrderService.getAll();
    }
}
