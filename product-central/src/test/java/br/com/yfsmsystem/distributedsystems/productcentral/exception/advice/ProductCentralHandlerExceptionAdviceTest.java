package br.com.yfsmsystem.distributedsystems.productcentral.exception.advice;

import br.com.yfsmsystem.distributedsystems.productcentral.exception.ProductNotFound;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.http.HttpMethod;

import java.util.Collections;

import static org.mockito.Mockito.*;

class ProductCentralHandlerExceptionAdviceTest {
    @Mock
    Log pageNotFoundLogger;
    @Mock
    Log logger;
    @InjectMocks
    ProductCentralHandlerExceptionAdvice productCentralHandlerExceptionAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testNotFoundException() {
        ResponseEntity<Object> result = productCentralHandlerExceptionAdvice
                .notFoundException(new ProductNotFound());
        Assertions.assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void testHandleMethodNotAllowedExceptionException() {
        ResponseEntity<Object> result = productCentralHandlerExceptionAdvice
                .handleMethodNotAllowedExceptionException(new MethodNotAllowedException("",
                        Collections.singleton(HttpMethod.GET)));
        Assertions.assertEquals(405, result.getStatusCodeValue());
    }
}