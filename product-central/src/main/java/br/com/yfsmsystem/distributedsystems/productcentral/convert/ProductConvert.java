package br.com.yfsmsystem.distributedsystems.productcentral.convert;


import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvert {

    protected ProductConvert() {
        throw new IllegalStateException("Utility class");
    }


    public static ProductDto mapEntityToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }


    public static Product mapDtoToEntity(ProductDto product) {
        return Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .central(true)
                .build();
    }

    public static List<ProductDto> mapListEntityToListDto(List<Product> products) {
        return products.stream().map(x -> ProductDto.builder()
                .name(x.getName())
                .price(x.getPrice())
                .build()).collect(Collectors.toList());
    }
}
