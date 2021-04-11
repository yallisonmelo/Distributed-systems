package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductAlreadyExistException;

public interface CreateProductUseCase {

   Product execute(Product product) throws ProductAlreadyExistException;
}
