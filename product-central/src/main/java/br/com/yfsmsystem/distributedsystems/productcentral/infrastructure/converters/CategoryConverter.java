package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.CategoryEntity;

public class CategoryConverter {


  public static CategoryEntity mapObjectToEntity(Category category) {
    return CategoryEntity.builder()
        .active(category.isActive())
        .description(category.getDescription())
        .discount(category.getDiscount())
        .build();
  }

  public static Category mapEntitytoObject(CategoryEntity categoryEntity) {
    return Category.builder()
        .id(categoryEntity.getId())
        .active(categoryEntity.isActive())
        .description(categoryEntity.getDescription())
        .discount(categoryEntity.getDiscount())
        .build();
  }

}
