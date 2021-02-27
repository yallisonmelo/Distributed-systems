package br.com.yfsmsystem.distributedsystems.productcentral.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErroObjectReturn {

    private String nameApplication;
    private String trace;
}
