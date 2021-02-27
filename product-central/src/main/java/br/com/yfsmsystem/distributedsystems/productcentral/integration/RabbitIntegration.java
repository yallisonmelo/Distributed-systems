package br.com.yfsmsystem.distributedsystems.productcentral.integration;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitIntegration {

    @Value("${exchange.central}")
    private String exchangeCentral;

    private final RabbitTemplate rabbitTemplate;

    public void sendProductForBranchs(ProductDto productDto) {
        rabbitTemplate.convertAndSend(exchangeCentral,"", productDto, m -> {
            m.getMessageProperties().setContentType("application/json");
            return m;
        });
    }
}
