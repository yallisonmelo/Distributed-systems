package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.CategoryRestIn;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.CategoryRestOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class CategoryConvertDeliveryTest {

  private static final String DESCRIPTION = "category";
  private static final int DISCOUNT = 10;
  private static final Long ID = 1L;
  private static final Boolean ACTIVE = true;


  private Category category;
  private CategoryRestOut categoryRestOut;
  private CategoryRestIn categoryRestIn;

  @BeforeEach
  void setUp() {

    category = Category.builder()
        .discount(DISCOUNT)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .id(ID)
        .build();

    categoryRestOut = CategoryRestOut.builder()
        .discount(DISCOUNT)
        .description(DESCRIPTION)
        .active(ACTIVE)
        .id(ID)
        .build();

    categoryRestIn = CategoryRestIn.builder()
        .active(ACTIVE)
        .description(DESCRIPTION)
        .discount(DISCOUNT)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMapObjectToRestOut() {
    CategoryRestOut result = CategoryConvertDelivery
        .mapObjectToRestOut(category);
    Assertions.assertEquals(categoryRestOut.getDescription(), result.getDescription());
    Assertions.assertEquals(categoryRestOut.getDiscount(), result.getDiscount());
    Assertions.assertEquals(categoryRestOut.getId(), result.getId());
  }

  @Test
  void testMapRestInToObject() {
    Category result = CategoryConvertDelivery.mapRestInToObject(categoryRestIn);
    Assertions.assertEquals(category.getDescription(), result.getDescription());
    Assertions.assertEquals(category.getDiscount(), result.getDiscount());
  }

  @Test
  void testMapListObjectToListOut() {
    List<CategoryRestOut> result = CategoryConvertDelivery.mapListObjectToListOut(
        Collections.singletonList(category));
    Assertions.assertEquals(Collections.singletonList(categoryRestOut).get(0).getDescription(),result.get(0).getDescription());
    Assertions.assertEquals(Collections.singletonList(categoryRestOut).get(0).getDiscount(),result.get(0).getDiscount());
  }
}

