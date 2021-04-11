package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters;

import lombok.val;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.ProductInRest;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.ProductOutRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ProductConvertDeliveryTest {

  private final static String NAME = "product";
  private final static Long ID = 1L;
  private final static Long ID_CATEGORY = 2L;
  private final static Double PRICE = 3.87;


  private Product product;
  private ProductOutRest productOutRest;
  private ProductInRest productInRest;

  @BeforeEach
  void setUp() {
    product = Product.builder()
        .name(NAME)
        .id(ID)
        .price(PRICE)
        .idCategory(ID_CATEGORY)
        .build();

    productOutRest = ProductOutRest.builder()
        .name(NAME)
        .id(ID)
        .price(PRICE)
        .build();

    productInRest = ProductInRest.builder()
        .name(NAME)
        .price(PRICE)
        .build();
  }

  @Test
  void testMapObjectToRestOut() {
    ProductOutRest result = ProductConvertDelivery.mapObjectToRestOut(product);
    Assertions.assertEquals(productOutRest.getPrice(), result.getPrice());
    Assertions.assertEquals(productOutRest.getName(), result.getName());
  }

  @Test
  void testMapRestInToProduct() {
    Product result = ProductConvertDelivery.mapRestInToProduct(productInRest);
    Assertions.assertEquals(product.getName(), result.getName());
    Assertions.assertEquals(product.getPrice(), result.getPrice());
  }

  @Test
  void testMapListObjectToListOut() {
    val products = Collections.singletonList(product);
    List<ProductOutRest> result = ProductConvertDelivery.mapListObjectToListOut(products);
    Assertions.assertEquals(products.get(0).getPrice(), result.get(0).getPrice());
    Assertions.assertEquals(products.get(0).getName(), result.get(0).getName());
  }
}
