package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;

public interface UpdateCategoryUseCase {

  Category execute(Long id, Category category) throws CategoryNotFound;

}
