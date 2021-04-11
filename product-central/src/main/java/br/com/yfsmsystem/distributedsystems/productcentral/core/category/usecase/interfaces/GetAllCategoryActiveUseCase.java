package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;

import java.util.List;

public interface GetAllCategoryActiveUseCase {

  List<Category> execute(Boolean status) ;

}
