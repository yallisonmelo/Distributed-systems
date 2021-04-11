package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;

import java.util.List;

public interface GetAllCategoryUseCase {

  List<Category> execute() throws CategoryNotFound;

}
