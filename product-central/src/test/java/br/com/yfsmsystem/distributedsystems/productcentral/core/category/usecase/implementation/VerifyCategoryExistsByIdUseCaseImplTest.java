package br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.implementation;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VerifyCategoryExistsByIdUseCaseImplTest {

  @Mock
  CategoryRepositoryService categoryRepositoryService;
  @InjectMocks
  VerifyCategoryExistsByIdUseCaseImpl verifyCategoryExistsByIdUseCaseImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testExecute() {
    when(categoryRepositoryService.verifyCategoryById(anyLong())).thenReturn(Boolean.TRUE);

    Boolean result = verifyCategoryExistsByIdUseCaseImpl.execute(anyLong());
    Assertions.assertEquals(Boolean.TRUE, result);
  }
}

