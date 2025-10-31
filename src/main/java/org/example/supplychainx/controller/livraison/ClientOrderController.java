package org.example.supplychainx.controller.livraison;

import org.example.supplychainx.dto.livraison.ClientOrderDTO;
import org.example.supplychainx.service.livraison.ClientOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/client-orders")
@RequiredArgsConstructor
public class ClientOrderController {

    private final ClientOrderService orderService;

    @PostMapping
    public ClientOrderDTO create(@RequestBody ClientOrderDTO dto) {
        return orderService.create(dto);
    }

    @PutMapping("/{id}")
    public ClientOrderDTO update(@PathVariable Long id, @RequestBody ClientOrderDTO dto) {
        return orderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping("/{id}")
    public ClientOrderDTO getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<ClientOrderDTO> getAll() {
        return orderService.getAll();
    }
}
