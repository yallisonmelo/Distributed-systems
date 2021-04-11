package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.ProductRepository;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteProductUseCaseImplementationTest {

  @Mock
  private ProductRepositoryService productRepositoryService;
  @Mock
  ProductRepository productRepository;
  @InjectMocks
  private DeleteProductUseCaseImplementation deleteProductUseCaseImplementation;

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
  void testExecuteSucess() {
    when(productRepositoryService.verifyProductExistsById(anyLong())).thenReturn(Boolean.TRUE);
    doNothing().when(productRepository).deleteById(anyLong());
    Assertions.assertDoesNotThrow(() -> deleteProductUseCaseImplementation.execute(anyLong()));
  }


  @Test
  void testExecuteException() {
    when(productRepositoryService.verifyProductExistsById(anyLong())).thenReturn(Boolean.FALSE);
    try {
      deleteProductUseCaseImplementation.execute(anyLong());
    } catch (ProductNotFound ex) {
      Assertions.assertEquals(CommonConstants.PRODUCT_NOT_FOUND, ex.getMessage());
    }
  }
}

