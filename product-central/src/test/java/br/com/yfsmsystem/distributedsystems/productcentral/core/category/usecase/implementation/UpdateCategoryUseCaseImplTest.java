package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.CategoryRepository;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateCategoryUseCaseImplTest {

  @Mock
  private CategoryRepositoryService categoryRepositoryService;
  @Mock
  private CategoryRepository categoryRepository;
  @InjectMocks
  private UpdateCategoryUseCaseImpl updateCategoryUseCaseImpl;

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
    when(categoryRepositoryService.updateCategory(anyLong(), any()))
        .thenReturn(category);
    Category result = updateCategoryUseCaseImpl.execute(anyLong(), any());
    Assertions.assertEquals(category, result);
  }


  @Test
  void testExecuteException() {
    when(categoryRepository.existsById(anyLong())).thenReturn(false);
    when(categoryRepositoryService.updateCategory(anyLong(), any()))
        .thenReturn(category);
    try {
      updateCategoryUseCaseImpl.execute(anyLong(), any());
    } catch (CategoryNotFound ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }
  }
}

