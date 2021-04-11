package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class GetAllCategoryActiveUseCaseImplTest {

  @Mock
  private CategoryRepositoryService categoryRepositoryService;
  @InjectMocks
  private GetAllCategoryActiveUseCaseImpl getAllCategoryActiveUseCaseImpl;
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
    when(categoryRepositoryService.getCategoryByActiveStatus(anyBoolean())).thenReturn(
        Collections.singletonList(category));

    List<Category> result = getAllCategoryActiveUseCaseImpl.execute(Boolean.TRUE);
    Assertions.assertEquals(
        Collections.singletonList(category), result);
  }

  @Test
  void testExecuteEmpityList() {
    when(categoryRepositoryService.getCategoryByActiveStatus(anyBoolean())).thenReturn(
        Collections.EMPTY_LIST);
    List<Category> result = getAllCategoryActiveUseCaseImpl.execute(Boolean.TRUE);
    Assertions.assertEquals(
        Collections.EMPTY_LIST.size(), result.size());

  }

}
