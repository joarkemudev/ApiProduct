package com.joarkemu.serviceTest;

import com.joarkemu.model.Product;
import com.joarkemu.repository.ProductRepository;
import com.joarkemu.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Product p1 = new Product(1, "Mouse", "Inalámbrico", 80.0);
        Product p2 = new Product(2, "Pantalla", "LED", 500.0);

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> products = productService.getAll();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductExists() {
        Product product = new Product(1, "CPU", "Intel", 1000.0);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findById(1);

        assertTrue(result.isPresent());
        assertEquals("CPU", result.get().getNombre());
    }

    @Test
    void testGetProductNotFound() {
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Product> result = productService.findById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1, "Teclado", "Mecánico", 200.0);
        Product savedProduct = new Product(1, "Teclado", "Mecánico", 200.0);

        when(productRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.save(product);

        assertNotNull(result.getId());
        assertEquals("Teclado", result.getNombre());
    }
}


