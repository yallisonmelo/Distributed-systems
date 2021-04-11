package br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.usecase.interfaces.VerifyCategoryExistsByIdUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.exception.ProductNotSavedException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.port.ProductRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.core.product.usecase.interfaces.CreateProductUseCase;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.configuration.RabbitIntegration;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants.CommonConstants;

@Service
@AllArgsConstructor
public class CreateProductUseCaseImplementation implements CreateProductUseCase {

  private final ProductRepositoryService productRepositoryService;
  private final RabbitIntegration rabbitIntegration;
  private final VerifyCategoryExistsByIdUseCase verifyCategoryExistsByIdUseCase;

  @Override
  public Product execute(Product product) throws ProductAlreadyExistException {
    if (Boolean.TRUE
        .equals(productRepositoryService.verifyProductExistsByName(product.getName()))) {
      throw new ProductAlreadyExistException();
    }
    if (verifyCategoryExistsByIdUseCase.execute(product.getIdCategory())) {
      rabbitIntegration.sendProductForBranchs(product);
      return productRepositoryService.saveProduct(product);
    } else {
      throw new ProductNotSavedException(CommonConstants.CATEGORY_NOT_FOUND);
    }
  }
}
