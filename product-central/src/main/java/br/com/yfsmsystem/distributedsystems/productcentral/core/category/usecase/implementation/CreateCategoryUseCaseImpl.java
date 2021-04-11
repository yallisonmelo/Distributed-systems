package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.CreateCategoryUseCase;

@Service
@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public Category execute(Category category) throws CategoryAlreadyExistException {
    return categoryRepositoryService.saveCategory(category);
  }
}
