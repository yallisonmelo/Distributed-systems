package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.UpdateProductUseCase;

@Service
@AllArgsConstructor
public class UpdateProductUseCaseImplementation implements UpdateProductUseCase {

  private final ProductRepositoryService productRepositoryService;

  @Override
  public Product execute(Long id, Product product) throws ProductNotFound {
    if (Boolean.TRUE.equals(productRepositoryService.verifyProductExistsById(id))) {
      return productRepositoryService.saveProduct(product);
    } else {
      throw new ProductNotFound();
    }
  }
}
