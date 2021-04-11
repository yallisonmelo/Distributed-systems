package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.DeleteProductUseCase;

@Service
@AllArgsConstructor
public class DeleteProductUseCaseImplementation implements DeleteProductUseCase {

  private final ProductRepositoryService productRepositoryService;

  @Override
  public void execute(Long id) {
    if (Boolean.TRUE.equals(productRepositoryService.verifyProductExistsById(id))) {
      productRepositoryService.deleteProduct(id);
    } else {
      throw new ProductNotFound();
    }
  }
}
