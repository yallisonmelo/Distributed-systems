package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.entrypoint.intefaces;

import org.springframework.http.ResponseEntity;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.CategoryRestIn;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.CategoryRestOut;

import java.util.List;

public interface CategoryController  {

  ResponseEntity<CategoryRestOut> getCategoryById(Long id);

  ResponseEntity<List<CategoryRestOut>> getAllCategories();

  ResponseEntity<CategoryRestOut> saveNewCategory(CategoryRestIn categoryRestIn);

  ResponseEntity<CategoryRestOut> updateCategory(Long id, CategoryRestIn categoryRestIn);

  ResponseEntity<Void> deleteCategory(Long id);
}
