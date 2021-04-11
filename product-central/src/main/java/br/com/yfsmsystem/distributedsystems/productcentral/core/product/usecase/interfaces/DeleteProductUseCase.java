package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;

public interface DeleteProductUseCase  {

  void execute(Long id) throws ProductNotFound;
}
