package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotSavedException;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.delivery.rest.exception.ErroObjectReturn;

@RestControllerAdvice
public class ProductCentralHandlerExceptionAdvice extends ResponseEntityExceptionHandler {

  @Value("${spring.application.name}")
  private String nameApp;

  @ExceptionHandler(ProductNotFound.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> productNotFoundException(ProductNotFound ex) {
    return ResponseEntity.status(404).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace(ex.getMessage())
        .build());
  }

  @ExceptionHandler(ProductAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
  public ResponseEntity<Object> productAlreadyExistException(ProductAlreadyExistException ex) {
    return ResponseEntity.status(409).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace(ex.getMessage())
        .build());
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  public ResponseEntity<Object> handleMethodNotAllowedExceptionException(
      MethodNotAllowedException ex) {
    return ResponseEntity.status(405).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace("Method not Allowed")
        .build());
  }


  @ExceptionHandler(CategoryNotFound.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> categoryNotFoundException(CategoryNotFound ex) {
    return ResponseEntity.status(404).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace(ex.getMessage())
        .build());
  }

  @ExceptionHandler(CategoryAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> categoryAlreadyExistException(CategoryAlreadyExistException ex) {
    return ResponseEntity.status(409).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace(ex.getMessage())
        .build());
  }

  @ExceptionHandler(ProductNotSavedException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> ProductNotSavedExceptionException(ProductNotSavedException ex) {
    return ResponseEntity.status(400).body(ErroObjectReturn
        .builder()
        .nameApplication(nameApp)
        .trace(ex.getMessage())
        .build());
  }
}
