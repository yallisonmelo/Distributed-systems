package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;

public interface GetProductByIdUseCase {

  Product execute(Long id) throws ProductNotFound;

}
