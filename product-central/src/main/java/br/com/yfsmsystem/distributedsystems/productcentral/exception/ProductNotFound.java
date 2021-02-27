package br.com.yfsmsystem.distributedsystems.productcentral.exception;

public class ProductNotFound  extends RuntimeException{
    public ProductNotFound() {
        super("Product not Found");
    }
}
