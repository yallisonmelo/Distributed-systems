package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.CategoryRestIn;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.CategoryRestOut;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConvertDelivery {


  public static CategoryRestOut mapObjectToRestOut(Category category) {
    return CategoryRestOut.builder()
        .active(category.isActive())
        .description(category.getDescription())
        .discount(category.getDiscount())
        .id(category.getId())
        .build();
  }

  public static Category mapRestInToObject(CategoryRestIn categoryRestIn) {
    return Category.builder()
        .active(categoryRestIn.isActive())
        .description(categoryRestIn.getDescription())
        .discount(categoryRestIn.getDiscount())
        .build();
  }

  public static List<CategoryRestOut> mapListObjectToListOut(List<Category> categories) {
    return categories.stream().map(CategoryConvertDelivery::mapObjectToRestOut)
        .collect(Collectors.toList());
  }
}
