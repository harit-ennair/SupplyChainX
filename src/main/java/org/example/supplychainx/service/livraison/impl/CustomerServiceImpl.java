package org.example.supplychainx.service.livraison.impl;

import org.example.supplychainx.dto.livraison.CustomerDTO;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.mapper.livraison.CustomerMapper;
import org.example.supplychainx.model.livraison.Customer;
import org.example.supplychainx.model.livraison.OrderStatusEnum;
import org.example.supplychainx.repository.livraison.ClientOrderRepository;
import org.example.supplychainx.repository.livraison.CustomerRepository;
import org.example.supplychainx.service.livraison.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ClientOrderRepository clientOrderRepository;
    private final CustomerMapper mapper;

    @Override
    public CustomerDTO create(CustomerDTO dto) {

        boolean exists = customerRepository.existsByEmailIgnoreCase(dto.getEmail());
        if (exists) {
            throw new BusinessException("Un client avec ce Email existe déjà : " + dto.getEmail());
        }

        Customer customer = mapper.toEntity(dto);
        return mapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {

        boolean exists = customerRepository.existsByEmailIgnoreCase(dto.getEmail());
        if (exists) {
            throw new BusinessException("Un client avec ce Email existe déjà : " + dto.getEmail());
        }

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Client introuvable (ID=" + id + ")"));

        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());

        return mapper.toDto(customerRepository.save(customer));
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Client introuvable"));

        boolean hasActiveOrders = clientOrderRepository.existsByCustomerIdCustomerAndStatusIn(
                id, Set.of(OrderStatusEnum.EN_PREPARATION, OrderStatusEnum.EN_ROUTE));

        if (hasActiveOrders) {
            throw new BusinessException("Impossible de supprimer le client : il a des commandes actives");
        }

        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Client introuvable !"));
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
