package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetAllCategoryActiveUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCategoryActiveUseCaseImpl implements GetAllCategoryActiveUseCase {

  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public List<Category> execute(Boolean status) {
    return categoryRepositoryService.getCategoryByActiveStatus(status);
  }
}
