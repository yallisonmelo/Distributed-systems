package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class ProductConvertTest {

  private final static String NAME = "product";
  private final static Long ID = 1L;
  private final static Long ID_CATEGORY = 2L;
  private final static Double PRICE = 3.87;


  private Product product;
  private ProductEntity productEntity;

  @BeforeEach
  void setUp() {
    product = Product.builder()
        .name(NAME)
        .id(ID)
        .price(PRICE)
        .idCategory(ID_CATEGORY)
        .build();

    productEntity = ProductEntity.builder()
        .name(NAME)
        .id(ID)
        .price(PRICE)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMapEntityToObject() {
    Product result = ProductConvert.mapEntityToObject(productEntity);
    Assertions.assertEquals(product.getPrice(), result.getPrice());
    Assertions.assertEquals(product.getName(), result.getName());
  }

  @Test
  void testMapObjectToEntity() {
    ProductEntity result = ProductConvert.mapObjectToEntity(product);
    Assertions.assertEquals(productEntity.getPrice(), result.getPrice());
    Assertions.assertEquals(productEntity.getName(), result.getName());
  }

  @Test
  void testMapEntityToDtoSendQueue() {
    Product result = ProductConvert.mapEntityToDtoSendQueue(product);
    Assertions.assertEquals(product.getName(), result.getName());
    Assertions.assertEquals(product.getId(), result.getId());
    Assertions.assertEquals(product.getPrice(), result.getPrice());
  }

  @Test
  void testMapListEntityToListObject() {
    List<Product> result = ProductConvert
        .mapListEntityToListObject(Collections.singletonList(productEntity));
    Assertions.assertEquals(Collections.singletonList(product).get(0).getName(),
        result.get(0).getName());
    Assertions.assertEquals(Collections.singletonList(product).get(0).getPrice(),
        result.get(0).getPrice());
  }

  @Test
  void testInstanceProductConvert() {
    try {
      ProductConvert pr = new ProductConvert();
    } catch (IllegalStateException ex) {
      Assertions.assertEquals("Utility class", ex.getMessage());
    }
  }
}