package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.implementations;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters.ProductConvert;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.ProductRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductRepositoryServiceImplementation implements ProductRepositoryService {

  private final ProductRepository productRepository;

  @Override
  @Cacheable("findAllProducts")
  public List<Product> getAllProducts() {
    return ProductConvert.mapListEntityToListObject(productRepository.findAll());
  }

  @Override
  public Product saveProduct(Product product) {

    ProductEntity productSaved = productRepository
        .save(ProductConvert.mapObjectToEntity(product));

    return ProductConvert.mapEntityToObject(productSaved);
  }

  @Override
  public Product getProductById(Long id) {
    return ProductConvert.mapEntityToObject(
        productRepository.findById(id)
            .orElseThrow(ProductNotFound::new));
  }

  @Override
  public Boolean verifyProductExistsById(Long id) {
    return productRepository.existsById(id);
  }

  @Override
  public Boolean verifyProductExistsByName(String name) {
    return productRepository.existsProductEntityByName(name);
  }

  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
