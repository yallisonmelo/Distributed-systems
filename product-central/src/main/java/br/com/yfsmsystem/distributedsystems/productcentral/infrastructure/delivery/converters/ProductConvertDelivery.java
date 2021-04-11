package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.converters;


import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in.ProductInRest;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out.ProductOutRest;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvertDelivery {

  protected ProductConvertDelivery() {
    throw new IllegalStateException("Utility class");
  }


  public static ProductOutRest mapObjectToRestOut(Product product) {
    return ProductOutRest.builder()
        .name(product.getName())
        .price(product.getPrice())
        .build();
  }

  public static Product mapRestInToProduct(ProductInRest productInRest) {
    return Product.builder()
        .name(productInRest.getName())
        .price(productInRest.getPrice())
        .idCategory(productInRest.getIdcategory())
        .build();
  }

  public static List<ProductOutRest> mapListObjectToListOut(List<Product> products) {
    return products.stream().map(x -> ProductOutRest.builder()
        .name(x.getName())
        .price(x.getPrice())
        .build()).collect(Collectors.toList());
  }
}
