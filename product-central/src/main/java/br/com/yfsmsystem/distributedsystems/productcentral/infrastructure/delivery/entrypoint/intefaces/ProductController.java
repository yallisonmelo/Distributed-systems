package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.entrypoint.intefaces;

import org.springframework.http.ResponseEntity;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.ProductInRest;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.ProductOutRest;

import java.util.List;

public interface ProductController {

  ResponseEntity<ProductOutRest> getProductbyId(Long id);

  ResponseEntity<List<ProductOutRest>> getAllProduct();

  ResponseEntity<ProductOutRest> saveNewProduct(ProductInRest productDto);

  ResponseEntity<ProductOutRest> updateProduct(Long id, ProductInRest productDto);

  ResponseEntity<Void> deleteProduct(Long id);
}
