package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.VerifyCategoryExistsByIdUseCase;

@Service
@AllArgsConstructor
public class VerifyCategoryExistsByIdUseCaseImpl implements VerifyCategoryExistsByIdUseCase {
  private final CategoryRepositoryService categoryRepositoryService;

  @Override
  public Boolean execute(Long id) throws CategoryNotFound {
    return categoryRepositoryService.verifyCategoryById(id);
  }
}
