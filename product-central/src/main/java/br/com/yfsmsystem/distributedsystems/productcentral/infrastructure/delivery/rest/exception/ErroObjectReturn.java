package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.exception;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErroObjectReturn {

  private String nameApplication;
  private String trace;
}
