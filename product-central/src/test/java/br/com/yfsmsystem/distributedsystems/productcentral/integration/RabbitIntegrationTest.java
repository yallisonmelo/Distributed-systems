package br.com.yfsmsystem.distributedsystems.productcentral.integration;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import com.google.common.base.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class RabbitIntegrationTest {
    @Mock
    RabbitTemplate rabbitTemplate;
    @InjectMocks
    RabbitIntegration rabbitIntegration;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {

        productDto = ProductDto.builder()
                .price(2.89)
                .name("rice")
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendProductForBranchs() {
       rabbitIntegration.sendProductForBranchs(productDto);
    }
}
