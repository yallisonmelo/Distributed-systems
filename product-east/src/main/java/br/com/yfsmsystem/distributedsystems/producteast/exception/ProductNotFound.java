package br.com.yfsmsystem.distributedsystems.producteast.exception;

public class ProductNotFound  extends RuntimeException{
    public ProductNotFound() {
        super("Product not Found");
    }
}
