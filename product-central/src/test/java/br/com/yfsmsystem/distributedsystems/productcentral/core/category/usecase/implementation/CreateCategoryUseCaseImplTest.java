package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.CategoryRepository;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateCategoryUseCaseImplTest {

  @Mock
  private CategoryRepositoryService categoryRepositoryService;
  @Mock
  private CategoryRepository categoryRepository;
  @InjectMocks
  private CreateCategoryUseCaseImpl createCategoryUseCaseimpl;

  private Category category;

  @BeforeEach
  void setUp() {
    category = Category.builder()
        .discount(10)
        .description("categoryTest")
        .id(2L)
        .active(true)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testExecuteSucess() {
    when(categoryRepositoryService.saveCategory(any()))
        .thenReturn(category);
    Category result = createCategoryUseCaseimpl
        .execute(category);
    Assertions.assertEquals(category, result);
  }


  @Test
  void testExecuteException() {
    when(categoryRepository.existsByDescription(any()))
        .thenReturn(true);
    when(categoryRepositoryService.saveCategory(any()))
        .thenReturn(category);
    try {
      Category result = createCategoryUseCaseimpl
          .execute(category);
    } catch (CategoryAlreadyExistException ex) {
      Assertions
          .assertEquals(CommonConstants.CATEGORY_ALREADY_EXISISTS, ex.getMessage());
    }
  }
}

