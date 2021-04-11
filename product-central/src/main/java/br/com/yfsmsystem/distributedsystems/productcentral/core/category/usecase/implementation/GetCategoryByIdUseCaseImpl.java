package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetCategoryByIdUseCase;

@Service
@AllArgsConstructor
public class GetCategoryByIdUseCaseImpl implements GetCategoryByIdUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public Category execute(Long id) throws CategoryAlreadyExistException {
    return categoryRepositoryService.getCategoryById(id);
  }
}
