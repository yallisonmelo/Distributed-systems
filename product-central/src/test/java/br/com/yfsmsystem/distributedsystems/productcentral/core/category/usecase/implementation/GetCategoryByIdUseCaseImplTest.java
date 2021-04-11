package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetCategoryByIdUseCaseImplTest {

  @Mock
  private CategoryRepositoryService categoryRepositoryService;
  @InjectMocks
  private GetCategoryByIdUseCaseImpl getCategoryByIdUseCaseImpl;

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
  void testExecute() {
    when(categoryRepositoryService.getCategoryById(anyLong()))
        .thenReturn(category);
    Category result = getCategoryByIdUseCaseImpl.execute(anyLong());
    Assertions.assertEquals(category,result);
  }
}

