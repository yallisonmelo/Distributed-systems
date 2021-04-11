package br.com.yfsmsystem.distributedsystems.productcentral.core.category.port;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;

import java.util.List;

public interface CategoryRepositoryService {

  Category saveCategory(Category category);

  Category getCategoryById(Long id);

  Category updateCategory(Long id,Category category);

  void deleteCategoryById(Long id);

  List<Category> getAllCategory();

  List<Category> getCategoryByActiveStatus(Boolean status);

  Boolean verifyCategoryById(Long id);
}
