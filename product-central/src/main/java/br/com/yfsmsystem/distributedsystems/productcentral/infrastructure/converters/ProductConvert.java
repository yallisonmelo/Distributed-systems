package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters;


import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvert {

    protected ProductConvert() {
        throw new IllegalStateException("Utility class");
    }


    public static Product mapEntityToObject(ProductEntity productEntity) {
        return Product.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
    }

    public static ProductEntity mapObjectToEntity(Product product) {
        return ProductEntity.builder()
            .name(product.getName())
            .price(product.getPrice())
            .build();
    }



    public static Product mapEntityToDtoSendQueue(Product product) {
        return Product.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .build();
    }


    public static List<Product> mapListEntityToListObject(List<ProductEntity> products) {
        return products.stream().map(x -> Product.builder()
                .name(x.getName())
                .price(x.getPrice())
                .build()).collect(Collectors.toList());
    }
}
