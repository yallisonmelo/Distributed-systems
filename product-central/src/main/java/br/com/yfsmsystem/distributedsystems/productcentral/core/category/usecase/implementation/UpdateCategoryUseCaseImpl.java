package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.UpdateCategoryUseCase;

@Service
@AllArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public Category execute(Long id, Category category) throws CategoryNotFound {
    return categoryRepositoryService.updateCategory(id,category);
  }
}
