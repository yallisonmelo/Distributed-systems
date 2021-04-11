package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters.ProductConvert;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.ProductEntity;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.ProductRepository;


@Component
@RequiredArgsConstructor
public class RabbitIntegration {

  @Value("${exchange.central}")
  private String exchangeCentral;


  private final RabbitTemplate rabbitTemplate;
  private final ProductRepository productRepository;

  public void sendProductForBranchs(Product product) {
    rabbitTemplate.convertAndSend(exchangeCentral, "create", product, m -> {
      m.getMessageProperties().setContentType("application/json");
      return m;
    });
  }

  @RabbitListener(queues = "${queue.product.delete}",
      containerFactory = "jsaFactory")
  public void send(@Payload Product product) {
    try {
      if (productRepository.existsById(product.getId())) {
        productRepository.deleteById(product.getId());
      } else {
        throw new ProductNotFound();
      }
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Problem save new Product, origin branch");
    }
  }


  @RabbitListener(queues = "${queue.product.origin.brachs}",
      containerFactory = "jsaFactory")
  public void receiveProductBrachs(@Payload Product product) {
    ProductEntity productEntity = ProductConvert.mapObjectToEntity(product);
    try {
      productRepository.save(productEntity);
      this.sendProductForBranchs(product);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Problem save new Product, origin branch");
    }

  }
}
