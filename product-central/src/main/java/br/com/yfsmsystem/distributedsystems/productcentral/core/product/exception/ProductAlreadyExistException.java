package br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException() {
        super(CommonConstants.PRODUCT_ALREADY_EXISISTS);
    }
}
