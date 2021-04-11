package br.com.yfsmsystem.distributedsystems.productcentral.core.product.port;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;

import java.util.List;

public interface ProductRepositoryService {

  List<Product> getAllProducts();

  Product saveProduct(Product product);

  Product getProductById(Long id);

  Boolean verifyProductExistsById(Long id);

  Boolean verifyProductExistsByName(String name);

  void deleteProduct(Long id);
}
