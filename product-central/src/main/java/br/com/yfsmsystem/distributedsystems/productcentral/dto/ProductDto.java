package br.com.yfsmsystem.distributedsystems.productcentral.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class ProductDto implements Serializable {

    private String name;

    private Double price;
}
