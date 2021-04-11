package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;

import java.util.List;

public interface GetAllProductsUseCase {

  List<Product> execute() throws ProductNotFound;
}
