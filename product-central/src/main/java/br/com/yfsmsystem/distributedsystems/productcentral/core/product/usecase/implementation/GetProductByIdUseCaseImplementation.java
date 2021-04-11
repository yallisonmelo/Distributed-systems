package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.GetProductByIdUseCase;

@Service

@AllArgsConstructor
public class GetProductByIdUseCaseImplementation implements GetProductByIdUseCase {

  private final ProductRepositoryService productRepositoryService;

  @Override
  public Product execute(Long id) throws ProductNotFound {
    return productRepositoryService.getProductById(id);
  }
}
