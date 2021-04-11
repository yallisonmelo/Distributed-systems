package br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;

public class CategoryAlreadyExistException extends RuntimeException {

  public CategoryAlreadyExistException() {
    super(CommonConstants.CATEGORY_ALREADY_EXISISTS);
  }
}

