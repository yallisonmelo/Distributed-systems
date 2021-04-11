package br.com.yfsmsystem.distributedsystems.productcentral.core.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {

    @JsonInclude(Include.NON_NULL)
    private Long id;
    private String name;
    private Double price;
    private Long idCategory;
}
