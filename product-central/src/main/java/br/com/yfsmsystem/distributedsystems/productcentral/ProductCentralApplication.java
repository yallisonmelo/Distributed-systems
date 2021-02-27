package br.com.yfsmsystem.distributedsystems.productcentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductCentralApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCentralApplication.class, args);
    }

}
