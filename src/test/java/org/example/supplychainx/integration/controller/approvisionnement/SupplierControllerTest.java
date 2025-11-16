package org.example.supplychainx.integration.controller.approvisionnement;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.supplychainx.model.approvisionnement.Supplier;
import org.example.supplychainx.repository.approvisionnement.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SupplierControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        supplierRepository.deleteAll();
    }

    @Test
    public void testCreateSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Test Supplier");

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Supplier"));
    }

    @Test
    public void getSupplierById_shouldReturn200() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Supplier A");
        supplier = supplierRepository.save(supplier);

        mockMvc.perform(get("/api/suppliers/" + supplier.getIdSupplier())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Supplier A"));
    }



}
