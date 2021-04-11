package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class GetAllProductsUseCaseImplementationTest {

  @Mock
  private ProductRepositoryService productRepositoryService;
  @InjectMocks
  private GetAllProductsUseCaseImplementation getAllProductsUseCaseImplementation;
  private Product product;

  @BeforeEach
  void setUp() {
    product = Product.builder()
        .idCategory(2L)
        .price(2.78)
        .id(1L)
        .name("productTest")
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testExecute() {
    when(productRepositoryService.getAllProducts()).thenReturn(
        Collections.singletonList(product));

    List<Product> result = getAllProductsUseCaseImplementation.execute();
    Assertions.assertEquals(
        Collections.singletonList(product), result);
  }
}

