package org.example.supplychainx.controller.production;

import org.example.supplychainx.dto.production.BillOfMaterialDTO;
import org.example.supplychainx.service.production.BillOfMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bill-of-materials")
@RequiredArgsConstructor
public class BillOfMaterialController {

    private final BillOfMaterialService billOfMaterialService;

    @PostMapping
    public BillOfMaterialDTO create(@RequestBody BillOfMaterialDTO dto) {
        return billOfMaterialService.create(dto);
    }

    @PutMapping("/{id}")
    public BillOfMaterialDTO update(@PathVariable Long id, @RequestBody BillOfMaterialDTO dto) {
        return billOfMaterialService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        billOfMaterialService.delete(id);
    }

    @GetMapping("/{id}")
    public BillOfMaterialDTO getById(@PathVariable Long id) {
        return billOfMaterialService.getById(id);
    }

    @GetMapping
    public List<BillOfMaterialDTO> getAll() {
        return billOfMaterialService.getAll();
    }
}
