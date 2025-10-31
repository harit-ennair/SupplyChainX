package org.example.supplychainx.controller.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplyMaterialDTO;
import org.example.supplychainx.service.approvisionnement.SupplyMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supply-materials")
@RequiredArgsConstructor
public class SupplyMaterialController {

    private final SupplyMaterialService supplyMaterialService;

    @PostMapping
    public SupplyMaterialDTO create(@RequestBody SupplyMaterialDTO dto) {
        return supplyMaterialService.create(dto);
    }

    @PutMapping("/{id}")
    public SupplyMaterialDTO update(@PathVariable Long id, @RequestBody SupplyMaterialDTO dto) {
        return supplyMaterialService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        supplyMaterialService.delete(id);
    }

    @GetMapping("/{id}")
    public SupplyMaterialDTO getById(@PathVariable Long id) {
        return supplyMaterialService.getById(id);
    }

    @GetMapping
    public List<SupplyMaterialDTO> getAll() {
        return supplyMaterialService.getAll();
    }
}
