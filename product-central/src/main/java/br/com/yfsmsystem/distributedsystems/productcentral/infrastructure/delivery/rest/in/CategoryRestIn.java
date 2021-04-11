package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class CategoryRestIn {

  @NotEmpty(message = "Please provide valid description.")
  @Length(max = 80, message = "The field has ${validatedValue} characters and exceeds the character limit, try a maximum of {max}")
  private String description;
  private Integer discount;
  private boolean active;
}
