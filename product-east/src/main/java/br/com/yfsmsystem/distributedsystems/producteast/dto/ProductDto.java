package br.com.yfsmsystem.distributedsystems.producteast.dto;

import br.com.yfsmsystem.distributedsystems.producteast.dto.interfaces.ProductDtoInsertByCentral;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable {

    @JsonView(ProductDtoInsertByCentral.class)
    private Long id;
    private String name;
    private Double price;
}
