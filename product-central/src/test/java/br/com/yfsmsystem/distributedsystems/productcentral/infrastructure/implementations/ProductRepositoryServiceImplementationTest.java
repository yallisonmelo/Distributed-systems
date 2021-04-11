package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.implementations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters.ProductConvert;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ProductRepositoryServiceImplementationTest {

  private final static String NAME = "product";
  private final static Long ID = 1L;
  private final static Double PRICE = 2.87;
  private final static Boolean CENTRAL = true;


  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private ProductRepositoryServiceImplementation productRepositoryServiceImplementation;
  private ProductEntity productEntity;
  private Product product;


  @BeforeEach
  void setUp() {
    product = Product.builder()
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    productEntity = ProductEntity.builder()
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .central(CENTRAL)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllProducts() {
    when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntity));
    List<Product> result = productRepositoryServiceImplementation.getAllProducts();
    Assertions.assertEquals(
        ProductConvert.mapListEntityToListObject(
            Collections.singletonList(productEntity)).toString(), result.toString());
  }

  @Test
  void testSaveProduct() {
    when(productRepository.save(any())).thenReturn(productEntity);
    Product result = productRepositoryServiceImplementation.saveProduct(product);
    Assertions
        .assertEquals(ProductConvert.mapEntityToObject(productEntity).toString(),
            result.toString());
  }

  @Test
  void testGetProductById() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
    Product result = productRepositoryServiceImplementation.getProductById(anyLong());
    Assertions.assertEquals(ProductConvert.mapEntityToObject(productEntity).toString(),result.toString());
  }

  @Test
  void testVerifyProductExistsById() {
    when(productRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
    Boolean result = productRepositoryServiceImplementation
        .verifyProductExistsById(anyLong());
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void testVerifyProductExistsByIdFalse() {
    when(productRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
    Boolean result = productRepositoryServiceImplementation
        .verifyProductExistsById(anyLong());
    Assertions.assertEquals(Boolean.FALSE, result);
  }

  @Test
  void testVerifyProductExistsByName() {
    when(productRepository.existsProductEntityByName(anyString())).thenReturn(Boolean.TRUE);
    Boolean result = productRepositoryServiceImplementation.verifyProductExistsByName("name");
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void testVerifyProductExistsByNameFalse() {
    when(productRepository.existsProductEntityByName(anyString())).thenReturn(Boolean.FALSE);
    Boolean result = productRepositoryServiceImplementation.verifyProductExistsByName("name");
    Assertions.assertEquals(Boolean.FALSE, result);
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productRepository).deleteById(anyLong());
    Assertions.assertDoesNotThrow(() ->
        productRepositoryServiceImplementation.deleteProduct(anyLong()));
  }
}
