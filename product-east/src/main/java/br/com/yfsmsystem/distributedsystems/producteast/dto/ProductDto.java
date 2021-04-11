package br.com.yfsmsystem.distributedsystems.producteast.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.yfsmsystem.distributedsystems.producteast.dto.interfaces.ProductDtoInsertByCentral;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable {

    @JsonView(ProductDtoInsertByCentral.class)
    @JsonInclude(Include.NON_NULL)
    private Long id;
    private String name;
    private Double price;
}
