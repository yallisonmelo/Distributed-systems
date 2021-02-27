package br.com.yfsmsystem.distributedsystems.productcentral.convert;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ProductConvertTest {

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    public void setup() {
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
    }

    @Test
    void testMapEntityToDto() {
        ProductDto result = ProductConvert.mapEntityToDto(product);
        Assertions.assertEquals(productDto.getPrice(), result.getPrice());
        Assertions.assertEquals(productDto.getName(), result.getName());
    }

    @Test
    void testMapDtoToEntity() {
        Product result = ProductConvert.mapDtoToEntity(productDto);
        Assertions.assertEquals(product.getPrice(), result.getPrice());
        Assertions.assertEquals(product.getName(), result.getName());
        Assertions.assertEquals(product.getCentral(), result.getCentral());
    }

    @Test
    void testMapListEntityToListDto() {
        List<ProductDto> result = ProductConvert
                .mapListEntityToListDto(Collections.singletonList(product));
        Assertions.assertEquals(Collections.singletonList(productDto).toString(),
                result.toString());
    }

    @Test
    void testProductConvertConstructor() {
        try {
            ProductConvert productConvert = new ProductConvert();
        } catch (IllegalStateException ex) {
            Assertions.assertEquals("Utility class", ex.getMessage());
        }
    }
}
