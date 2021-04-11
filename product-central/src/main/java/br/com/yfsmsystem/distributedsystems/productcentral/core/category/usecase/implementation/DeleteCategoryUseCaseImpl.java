package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.DeleteCategoryUseCase;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public void execute(Long id) throws CategoryNotFound {
    categoryRepositoryService.deleteCategoryById(id);
  }
}
