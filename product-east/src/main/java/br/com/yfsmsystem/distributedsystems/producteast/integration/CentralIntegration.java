package br.com.yfsmsystem.distributedsystems.producteast.integration;

import br.com.yfsmsystem.distributedsystems.producteast.convert.ProductConvert;
import br.com.yfsmsystem.distributedsystems.producteast.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.producteast.entity.Product;
import br.com.yfsmsystem.distributedsystems.producteast.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CentralIntegration {

    @Value("${queue.central}")
    private String queueCentral;

    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"${queue.branch-east}"})
    public void receive(@Payload ProductDto productDto) {
        Product product = ProductConvert.mapDtoToEntitySetCentral(productDto);
        productService.saveOrUpdateProductOriginCentral(product);
    }


    public void sendProductForCentral(ProductDto productDto) {
        rabbitTemplate.convertAndSend(queueCentral, productDto, m -> {
            m.getMessageProperties().setContentType("application/json");
            return m;
        });
    }

}
