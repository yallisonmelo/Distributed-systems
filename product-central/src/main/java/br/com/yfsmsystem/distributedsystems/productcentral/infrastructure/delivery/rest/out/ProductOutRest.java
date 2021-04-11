package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class ProductOutRest implements Serializable {

    @JsonInclude(Include.NON_NULL)
    private Long id;
    @NotEmpty(message = "Please provide valid Name.")
    @Length(max = 80, message = "The field has ${validatedValue} characters and exceeds the character limit, try a maximum of {max}")
    private String name;
    private Double price;
}
