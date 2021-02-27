package br.com.yfsmsystem.distributedsystems.productcentral.service;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.integration.RabbitIntegration;
import br.com.yfsmsystem.distributedsystems.productcentral.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static final String MESSAGENOTFOUND = "Product not Found";

    @Mock
    private ProductRepository productRepository;
    @Mock
    private RabbitIntegration rabbitIntegration;
    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("rice")
                .price(2.89)
                .central(true)
                .build();

        productDto = ProductDto.builder()
                .price(2.89)
                .name("rice")
                .build();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById() {
        BDDMockito.given(productRepository.findById(anyLong())).willReturn(Optional.of(product));
        ProductDto result = productService.getProductById(anyLong());
        Assertions.assertEquals(productDto.toString(), result.toString());
    }

    @Test
    void testGetAllProducts() {
        BDDMockito.given(productRepository.findAll()).willReturn(Collections.singletonList(product));
        List<ProductDto> result = productService.getAllProducts();
        Assertions.assertEquals(Collections.<ProductDto>singletonList(productDto).toString(), result.toString());
    }

    @Test
    void testSaveNewProduct() {
        doNothing().when(rabbitIntegration).sendProductForBranchs(productDto);
        BDDMockito.given(productRepository.save(any())).willReturn(product);
        ProductDto result = productService.saveNewProduct(productDto);
        Assertions.assertEquals(productDto.toString(), result.toString());
    }


    @Test
    void testSaveNewProductException() {
        doNothing().when(rabbitIntegration).sendProductForBranchs(productDto);
        BDDMockito.given(productRepository.save(any())).willAnswer(invocation -> {
            throw new Exception("abc msg");
        });
        try {
            productService.saveNewProduct(productDto);
        } catch (Exception e) {
            Assertions
                    .assertEquals(400, ((ResponseStatusException) e).getRawStatusCode());
        }
    }


    @Test
    void testUpdateProduct() {
        BDDMockito.given(productRepository.existsById(anyLong())).willReturn(true);
        BDDMockito.given(productRepository.save(any(Product.class))).willReturn(product);
        ProductDto result = productService.updateProduct(anyLong(), productDto);
        Assertions.assertEquals(productDto.toString(), result.toString());
    }

    @Test
    void testUpdateProductException() {
        BDDMockito.given(productRepository.existsById(anyLong())).willReturn(false);
        try {
            productService.updateProduct(anyLong(), productDto);
        } catch (ProductNotFound e) {
            Assertions.assertEquals(MESSAGENOTFOUND, e.getMessage());
        }
    }

    @Test
    void testDeleteProduct() {
        BDDMockito.given(productRepository.existsById(anyLong())).willReturn(true);
        doNothing().when(productRepository).deleteById(anyLong());
        Assertions.assertDoesNotThrow(() -> productService.deleteProduct(anyLong()));
    }


    @Test
    void testDeleteProductException() {
        BDDMockito.given(productRepository.existsById(anyLong())).willReturn(false);
        try {
            productService.deleteProduct(anyLong());
        } catch (ProductNotFound e) {
            Assertions.assertEquals(MESSAGENOTFOUND, e.getMessage());
        }
    }

}
