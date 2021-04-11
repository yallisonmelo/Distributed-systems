package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryRestOut {

  private Long id;
  private String description;
  private Integer discount;
  private boolean active;
}
