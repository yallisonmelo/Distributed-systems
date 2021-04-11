package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetProductByIdUseCaseImplementationTest {

  @Mock
  private ProductRepositoryService productRepositoryService;
  @InjectMocks
  private GetProductByIdUseCaseImplementation getProductByIdUseCaseImplementation;
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
    when(productRepositoryService.getProductById(anyLong()))
        .thenReturn(product);

    Product result = getProductByIdUseCaseImplementation.execute(anyLong());
    Assertions
        .assertEquals(product, result);
  }
}

