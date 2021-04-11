package br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception;

import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;

public class CategoryNotFound extends RuntimeException {

  public CategoryNotFound() {
    super(CommonConstants.CATEGORY_NOT_FOUND);
  }
}

