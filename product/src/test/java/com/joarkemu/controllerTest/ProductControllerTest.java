package com.joarkemu.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joarkemu.controller.ProductController;
import com.joarkemu.model.Product;
import com.joarkemu.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService; // <-- ESTA ANOTACIÓN ES CLAVE

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1);
        product.setNombre("Laptop");
        product.setDescripcion("Laptop Lenovo");
        product.setPrecio(1500.00);
    }

    @Test
    void testGetAll() throws Exception {
        List<Product> products = Arrays.asList(product);
        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/products/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Laptop"));
    }

    @Test
    void testGetById() throws Exception {
        when(productService.findById(1)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Laptop"));
    }

    @Test
    void testSave() throws Exception {
        when(productService.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nombre").value("Laptop"));
    }
}
