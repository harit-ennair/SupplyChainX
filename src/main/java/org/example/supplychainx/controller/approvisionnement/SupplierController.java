package org.example.supplychainx.controller.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.SupplierDTO;
import org.example.supplychainx.service.approvisionnement.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public SupplierDTO create(@RequestBody SupplierDTO dto) {
        return supplierService.create(dto);
    }

    @PutMapping("/{id}")
    public SupplierDTO update(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        return supplierService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        supplierService.delete(id);
    }

    @GetMapping("/{id}")
    public SupplierDTO getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @GetMapping
    public List<SupplierDTO> getAll() {
        return supplierService.getAll();
    }
}
