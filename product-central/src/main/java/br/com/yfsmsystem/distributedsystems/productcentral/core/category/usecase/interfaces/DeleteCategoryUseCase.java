package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;

public interface DeleteCategoryUseCase {

  void execute(Long id) throws CategoryNotFound;
}
