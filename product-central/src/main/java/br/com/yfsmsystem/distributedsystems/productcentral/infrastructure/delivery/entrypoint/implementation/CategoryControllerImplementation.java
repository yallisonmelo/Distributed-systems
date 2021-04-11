package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.entrypoint.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.CreateCategoryUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.DeleteCategoryUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetAllCategoryActiveUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetAllCategoryUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.GetCategoryByIdUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.UpdateCategoryUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters.CategoryConvertDelivery;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.entrypoint.intefaces.CategoryController;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.CategoryRestIn;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.CategoryRestOut;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryControllerImplementation implements CategoryController {

  private final CreateCategoryUseCase createCategoryUseCase;
  private final DeleteCategoryUseCase deleteCategoryUseCase;
  private final GetAllCategoryActiveUseCase getAllCategoryActiveUseCase;
  private final GetAllCategoryUseCase getAllCategoryUseCase;
  private final GetCategoryByIdUseCase getCategoryByIdUseCase;
  private final UpdateCategoryUseCase updateCategoryUseCase;


  @Override
  @GetMapping("/{id}")
  public ResponseEntity<CategoryRestOut> getCategoryById(@PathVariable  Long id) {
    return ResponseEntity.ok(CategoryConvertDelivery
        .mapObjectToRestOut(getCategoryByIdUseCase.execute(id)));
  }

  @Override
  @GetMapping
  public ResponseEntity<List<CategoryRestOut>> getAllCategories() {
    return ResponseEntity.ok(CategoryConvertDelivery
        .mapListObjectToListOut(getAllCategoryUseCase.execute()));
  }

  @Override
  @PostMapping
  public ResponseEntity<CategoryRestOut> saveNewCategory(@RequestBody @Valid CategoryRestIn categoryRestIn) {

    return ResponseEntity.ok(
        CategoryConvertDelivery.mapObjectToRestOut(
            createCategoryUseCase
                .execute(CategoryConvertDelivery
                    .mapRestInToObject(categoryRestIn))));

  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<CategoryRestOut> updateCategory(@PathVariable Long id, @RequestBody CategoryRestIn categoryRestIn) {
    return ResponseEntity.ok(
        CategoryConvertDelivery.mapObjectToRestOut(
            updateCategoryUseCase
                .execute(id, CategoryConvertDelivery
                    .mapRestInToObject(categoryRestIn))));

  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    deleteCategoryUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}

