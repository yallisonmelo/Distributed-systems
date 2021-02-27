package br.com.yfsmsystem.distributedsystems.productcentral.controller;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.service.ProductService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;


class ProductControllerTest {
    private final static String PATH = "/product/central/";

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;


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
    void testGetProductbyId() {
        BDDMockito.given(productService.getProductById(anyLong()))
                .willReturn(productDto);
        Assertions.assertEquals(
                ResponseEntity.ok(productDto)
                , productController.getProductbyId(anyLong()));

    }

    @Test
    void testGetAllProduct() {
        BDDMockito.given(productService.getAllProducts())
                .willReturn(Collections.singletonList(productDto));
        val returnList = Collections.singletonList(productDto);
        Assertions.assertEquals(ResponseEntity.ok(returnList),
                productController.getAllProduct());
    }

    @Test
    void testSaveNewProduct() {
        BDDMockito.given(productService.saveNewProduct(any()))
                .willReturn(productDto);
        Assertions.assertEquals(ResponseEntity.ok(productDto),
                productController.saveNewProduct(productDto));
    }

    @Test
    void testUpdateProduct() {
        BDDMockito.given(productService.updateProduct(anyLong(),any(ProductDto.class)))
                .willReturn(productDto);
        Assertions.assertEquals(ResponseEntity.ok(productDto),
                productController.updateProduct(1L, productDto));
    }

    @Test
    void testDeleteProduct() {
        Assertions.assertDoesNotThrow(() -> productController.deleteProduct(anyLong()));
    }
}
