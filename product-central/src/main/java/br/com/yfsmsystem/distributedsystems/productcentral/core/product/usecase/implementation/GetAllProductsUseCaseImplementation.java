package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.GetAllProductsUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllProductsUseCaseImplementation implements GetAllProductsUseCase {

  private final ProductRepositoryService productRepositoryService;

  @Override
  public List<Product> execute() throws ProductNotFound {
    return productRepositoryService.getAllProducts();
  }
}
