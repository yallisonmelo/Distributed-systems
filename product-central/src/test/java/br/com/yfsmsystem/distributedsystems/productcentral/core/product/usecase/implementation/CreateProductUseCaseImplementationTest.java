package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.VerifyCategoryExistsByIdUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotSavedException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.configuration.RabbitIntegration;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateProductUseCaseImplementationTest {

  @Mock
  private ProductRepositoryService productRepositoryService;
  @Mock
  private RabbitIntegration rabbitIntegration;
  @Mock
  private VerifyCategoryExistsByIdUseCase verifyCategoryExistsByIdUseCase;
  @InjectMocks
  private CreateProductUseCaseImplementation createProductUseCaseImplementation;

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
    when(productRepositoryService.saveProduct(any()))
        .thenReturn(product);
    when(productRepositoryService.verifyProductExistsByName(anyString())).thenReturn(Boolean.FALSE);
    when(verifyCategoryExistsByIdUseCase.execute(anyLong())).thenReturn(Boolean.TRUE);

    Product result = createProductUseCaseImplementation
        .execute(product);
    Assertions
        .assertEquals(product, result);
  }


  @Test
  void testExecuteExceptionAlreadyExists() {
    when(productRepositoryService.saveProduct(any()))
        .thenReturn(product);
    when(productRepositoryService.verifyProductExistsByName(anyString())).thenReturn(Boolean.TRUE);
    when(verifyCategoryExistsByIdUseCase.execute(anyLong())).thenReturn(Boolean.TRUE);
    try {
      createProductUseCaseImplementation
          .execute(product);
    } catch (ProductAlreadyExistException ex) {
      Assertions
          .assertEquals(CommonConstants.PRODUCT_ALREADY_EXISISTS, ex.getMessage());
    }
  }



  @Test
  void testExecuteExceptionCategoryNotFound() {
    when(productRepositoryService.saveProduct(any()))
        .thenReturn(product);
    when(productRepositoryService.verifyProductExistsByName(anyString())).thenReturn(Boolean.FALSE);
    when(verifyCategoryExistsByIdUseCase.execute(anyLong())).thenReturn(Boolean.FALSE);
    try {
      createProductUseCaseImplementation
          .execute(product);
    } catch (ProductNotSavedException ex) {
      Assertions
          .assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }
  }
}

