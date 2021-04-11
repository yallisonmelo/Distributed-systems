package br.com.yfsmsystem.distributedsystems.producteast.integration;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.yfsmsystem.distributedsystems.producteast.convert.ProductConvert;
import br.com.yfsmsystem.distributedsystems.producteast.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.producteast.entity.Product;
import br.com.yfsmsystem.distributedsystems.producteast.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class CentralIntegration {

    @Value("${queue.central}")
    private String queueCentral;

    private final ProductRepository productRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"${queue.branch-east}"}, containerFactory = "jsaFactory")
    public void receive(@Payload ProductDto productDto,
        @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        System.out.println(key);
        Product product = ProductConvert.mapDtoToEntitySetCentral(productDto);
        productRepository.save(product);
    }

    public void sendProductForCentral(ProductDto productDto) {
        rabbitTemplate.convertAndSend(queueCentral, productDto, m -> {
            m.getMessageProperties().setContentType("application/json");
            return m;
        });
    }

}
