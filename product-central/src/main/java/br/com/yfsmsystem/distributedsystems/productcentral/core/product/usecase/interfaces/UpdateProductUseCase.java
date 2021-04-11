package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;

public interface UpdateProductUseCase {

  Product execute(Long id, Product product) throws ProductNotFound;
}
