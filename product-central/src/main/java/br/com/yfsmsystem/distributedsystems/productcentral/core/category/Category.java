package br.com.yfsmsystem.distributedsystems.productcentral.core.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

  private Long id;
  private String description;
  private Integer discount;
  private boolean active;

}
