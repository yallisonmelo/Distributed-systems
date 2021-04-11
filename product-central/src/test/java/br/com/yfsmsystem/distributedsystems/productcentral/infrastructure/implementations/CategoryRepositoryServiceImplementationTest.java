package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.implementations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.CategoryEntity;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.CategoryRepository;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CategoryRepositoryServiceImplementationTest {

  private static final String DESCRIPTION = "category";
  private static final int DISCOUNT = 10;
  private static final Long ID = 1L;
  private static final Boolean ACTIVE = true;

  @Mock
  private CategoryRepository categoryRepository;
  @InjectMocks
  private CategoryRepositoryServiceImplementation categoryRepositoryServiceImplementation;

  private Category category;
  private CategoryEntity categoryEntity;


  @BeforeEach
  void setUp() {
    category = Category.builder()
        .discount(DISCOUNT)
        .id(ID)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .build();

    categoryEntity = CategoryEntity.builder()
        .discount(DISCOUNT)
        .id(ID)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .build();

    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveCategory() {
    when(categoryRepository.existsByDescription(anyString())).thenReturn(Boolean.FALSE);
    when(categoryRepository.save(any())).thenReturn(categoryEntity);
    Category result = categoryRepositoryServiceImplementation
        .saveCategory(category);
    Assertions.assertEquals(category.getDescription(), result.getDescription());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
    Assertions.assertEquals(category.getId(), result.getId());
  }

  @Test
  void testSaveCategoryException() {
    when(categoryRepository.existsByDescription(anyString())).thenReturn(Boolean.TRUE);
    try {
      categoryRepositoryServiceImplementation
          .saveCategory(category);
    } catch (CategoryAlreadyExistException ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_ALREADY_EXISISTS, ex.getMessage());
    }

  }

  @Test
  void testGetCategoryById() {
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(categoryEntity));
    Category result = categoryRepositoryServiceImplementation.getCategoryById(anyLong());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
    Assertions.assertEquals(category.getDescription(), result.getDescription());
  }

  @Test
  void testGetCategoryByIdException() {
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
    try {
      categoryRepositoryServiceImplementation.getCategoryById(anyLong());
    } catch (CategoryNotFound ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }

  }

  @Test
  void testUpdateCategory() {
    when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
    when(categoryRepository.save(any())).thenReturn(categoryEntity);
    Category result = categoryRepositoryServiceImplementation.updateCategory(ID, category);
    Assertions.assertEquals(category.getDescription(), result.getDescription());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
    Assertions.assertEquals(category.getId(), result.getId());
  }

  @Test
  void testUpdateCategoryException() {
    when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
    try {
      categoryRepositoryServiceImplementation.updateCategory(ID, category);
    } catch (CategoryNotFound ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }
  }

  @Test
  void testDeleteCategoryById() {
    when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
    doNothing().when(categoryRepository).deleteById(anyLong());
    Assertions.assertDoesNotThrow(
        () -> categoryRepositoryServiceImplementation.deleteCategoryById(anyLong()));
  }


  @Test
  void testDeleteCategoryByIdException() {
    when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
    try {
      categoryRepositoryServiceImplementation.deleteCategoryById(anyLong());
    } catch (CategoryNotFound ex) {
      Assertions.assertEquals(CommonConstants.CATEGORY_NOT_FOUND, ex.getMessage());
    }
  }

  @Test
  void testGetAllCategory() {
    when(categoryRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
    List<Category> result = categoryRepositoryServiceImplementation.getAllCategory();
    Assertions.assertNotNull(result);
  }

  @Test
  void testGetCategoryByActiveStatus() {
    when(categoryRepository.findAllByActive(anyBoolean())).thenReturn(
        Collections.singletonList(categoryEntity));

    List<Category> result = categoryRepositoryServiceImplementation
        .getCategoryByActiveStatus(Boolean.TRUE);

    Assertions.assertNotNull(result);
  }

  @Test
  void testVerifyCategoryById() {
    when(categoryRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
    Boolean result = categoryRepositoryServiceImplementation.verifyCategoryById(ID);
    Assertions.assertEquals(Boolean.TRUE, result);
  }
}
