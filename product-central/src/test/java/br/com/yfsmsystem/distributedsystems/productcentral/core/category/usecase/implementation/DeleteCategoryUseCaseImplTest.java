package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

class DeleteCategoryUseCaseImplTest {

  @Mock
  private CategoryRepositoryService categoryRepositoryService;
  @Mock
  private CategoryRepository categoryRepository;
  @InjectMocks
  private DeleteCategoryUseCaseImpl deleteCategoryUseCaseImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testExecuteSucess() {
    when(categoryRepository.existsById(anyLong())).thenReturn(true);
    doNothing().when(categoryRepository).deleteById(anyLong());
    Assertions.assertDoesNotThrow(() -> deleteCategoryUseCaseImpl.execute(anyLong()));
  }

  @Test
  void testExecuteException() {
    when(categoryRepository.existsById(anyLong())).thenReturn(false);
    doNothing().when(categoryRepository).deleteById(anyLong());
    try {
      deleteCategoryUseCaseImpl.execute(anyLong());
    } catch (CategoryNotFound ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }
  }
}
