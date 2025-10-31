package org.example.supplychainx.controller.approvisionnement;

import org.example.supplychainx.dto.approvisionnement.RawMaterialDTO;
import org.example.supplychainx.service.approvisionnement.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@RequiredArgsConstructor
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @PostMapping
    public RawMaterialDTO create(@RequestBody RawMaterialDTO dto) {
        return rawMaterialService.create(dto);
    }

    @PutMapping("/{id}")
    public RawMaterialDTO update(@PathVariable Long id, @RequestBody RawMaterialDTO dto) {
        return rawMaterialService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
    }

    @GetMapping("/{id}")
    public RawMaterialDTO getById(@PathVariable Long id) {
        return rawMaterialService.getById(id);
    }

    @GetMapping
    public List<RawMaterialDTO> getAll() {
        return rawMaterialService.getAll();
    }
}
