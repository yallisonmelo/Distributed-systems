package br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound() {
        super(CommonConstants.PRODUCT_NOT_FOUND);
    }
}
