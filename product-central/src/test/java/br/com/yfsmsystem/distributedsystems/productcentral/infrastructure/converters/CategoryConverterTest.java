package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.CategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class CategoryConverterTest {

  private static final String DESCRIPTION = "category";
  private static final int DISCOUNT = 10;
  private static final Long ID = 1L;
  private static final Boolean ACTIVE = true;


  private Category category;
  private CategoryEntity categoryEntity;


  @BeforeEach
  void setUp() {

    categoryEntity = CategoryEntity.builder()
        .discount(DISCOUNT)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .id(ID)
        .build();

    category = Category.builder()
        .discount(DISCOUNT)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .id(ID)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMapObjectToEntity() {
    CategoryEntity result = CategoryConverter
        .mapObjectToEntity(category);
    Assertions.assertEquals(category.getDescription(), result.getDescription());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
  }

  @Test
  void testMapEntitytoObject() {
    Category result = CategoryConverter.mapEntitytoObject(categoryEntity);
    Assertions.assertEquals(category.getDescription(), result.getDescription());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
    Assertions.assertEquals(category.getId(), result.getId());
  }
}
