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

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.CreateProductUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.DeleteProductUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.GetAllProductsUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.GetProductByIdUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.UpdateProductUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters.ProductConvertDelivery;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.entrypoint.intefaces.ProductController;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.ProductInRest;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.ProductOutRest;

import java.util.List;


@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductControllerImplementation implements ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final DeleteProductUseCase deleteProductUseCase;
  private final UpdateProductUseCase updateProductUseCase;
  private final GetProductByIdUseCase getProductByIdUseCase;
  private final GetAllProductsUseCase getAllProductsUseCase;

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ProductOutRest> getProductbyId(@PathVariable Long id) {
    return ResponseEntity.ok(ProductConvertDelivery
        .mapObjectToRestOut(getProductByIdUseCase.execute(id)));
  }

  @Override
  @GetMapping
  public ResponseEntity<List<ProductOutRest>> getAllProduct() {
    return ResponseEntity.ok(ProductConvertDelivery
        .mapListObjectToListOut(
        getAllProductsUseCase.execute()));
  }

  @Override
  @PostMapping
  public ResponseEntity<ProductOutRest> saveNewProduct(@RequestBody ProductInRest productInRest) {
    return ResponseEntity.ok(
        ProductConvertDelivery.mapObjectToRestOut(
            createProductUseCase
                .execute(ProductConvertDelivery.mapRestInToProduct(productInRest))));
  }

  @Override
  @PutMapping("{/id}")
  public ResponseEntity<ProductOutRest> updateProduct(@PathVariable Long id,
      @RequestBody ProductInRest productInRest) {
    return ResponseEntity.ok(
        ProductConvertDelivery.mapObjectToRestOut(
            updateProductUseCase.execute(id,
                ProductConvertDelivery.mapRestInToProduct(productInRest))
        )
    );
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(Long id) {
    deleteProductUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}
