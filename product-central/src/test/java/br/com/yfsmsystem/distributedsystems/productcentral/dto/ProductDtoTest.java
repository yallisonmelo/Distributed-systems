package br.com.yfsmsystem.distributedsystems.productcentral.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductDtoTest {

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = ProductDto.builder()
                .name("rice")
                .price(2.89)
                .build();
    }

    @Test
    void testBuilder() {
        ProductDto.ProductDtoBuilder result =
                ProductDto.builder();
        Assertions.assertNotNull(result);
    }

    @Test
    void testSetName() {
        productDto.setName("name");
        Assertions.assertEquals("name", productDto.getName());
    }

    @Test
    void testSetPrice() {
        productDto.setPrice(2.87);
        Assertions.assertEquals(2.87, productDto.getPrice());
    }

    @Test
    void testToString() {
        String result = productDto.toString();
        Assertions.assertEquals(productDto.toString(), result);
    }
}
