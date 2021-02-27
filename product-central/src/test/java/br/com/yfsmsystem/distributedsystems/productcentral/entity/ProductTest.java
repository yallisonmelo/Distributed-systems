package br.com.yfsmsystem.distributedsystems.productcentral.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testBuilder() {
        Product.ProductBuilder result = Product.builder();
        Assertions.assertNotNull( result);
    }

    @Test
    void testGetId() {
        Product product =Product.builder()
                .id(1L)
                .build();
        Assertions.assertEquals( 1L,product.getId());
    }

    @Test
    void testNoArgsConstructor(){
        Product product = new Product();
        Assertions.assertNotNull(product);
    }
}