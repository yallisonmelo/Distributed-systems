package br.com.yfsmsystem.distributedsystems.producteast.exception.advice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.yfsmsystem.distributedsystems.producteast.dto.ErroObjectReturn;
import br.com.yfsmsystem.distributedsystems.producteast.exception.ProductNotFound;

@RestControllerAdvice
public class ProductCentralHandlerExceptionAdvice extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    private String nameApp;

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public  ResponseEntity<Object> notFoundException(ProductNotFound ex) {
        return ResponseEntity.status(404).body(ErroObjectReturn
                .builder()
                .nameApplication(nameApp)
                .trace(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public   ResponseEntity<Object> handleMethodNotAllowedExceptionException(MethodNotAllowedException ex) {
        return ResponseEntity.status(404).body(ErroObjectReturn
                .builder()
                .nameApplication(nameApp)
                .trace("Method not Allowed")
                .build());
    }
}
