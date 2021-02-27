package br.com.yfsmsystem.distributedsystems.productcentral.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErroObjectReturnTest {

    @Test
    void testBuilder() {
        ErroObjectReturn result = ErroObjectReturn.builder()
                .trace("test")
                .nameApplication("central")
                .build();
        Assertions.assertEquals("central", result.getNameApplication());
        Assertions.assertEquals("test", result.getTrace());
    }
}