package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetAllCategoryUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCategoryUseCaseImpl implements GetAllCategoryUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public List<Category> execute() throws CategoryNotFound {
    return categoryRepositoryService.getAllCategory();
  }
}
