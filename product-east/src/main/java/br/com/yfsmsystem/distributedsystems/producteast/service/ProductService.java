package br.com.yfsmsystem.distributedsystems.producteast.service;


import br.com.yfsmsystem.distributedsystems.producteast.convert.ProductConvert;
import br.com.yfsmsystem.distributedsystems.producteast.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.producteast.entity.Product;
import br.com.yfsmsystem.distributedsystems.producteast.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.producteast.integration.CentralIntegration;
import br.com.yfsmsystem.distributedsystems.producteast.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CentralIntegration centralIntegration;

    public ProductDto getProductById(Long id) {
        return ProductConvert.mapEntityToDto(
                productRepository.findById(id)
                        .orElseThrow(ProductNotFound::new));
    }


    public ProductDto getProductByName(String name) {
        return ProductConvert.mapEntityToDto(
                productRepository.findProductByName(name)
                        .orElseThrow(ProductNotFound::new));
    }

    public List<ProductDto> getAllProducts() {
        return ProductConvert.mapListEntityToListDto(
                productRepository.findAll());
    }

    protected Boolean verifyProductExistBy(ProductDto productDto) {
        return productRepository.existsById(productDto.getId());
    }

    public void saveOrUpdateProductOriginCentral(Product product) {
        productRepository.save(product);
    }


    public ProductDto saveNewProduct(ProductDto productDto) {
        try {
            centralIntegration.sendProductForCentral(productDto);
            return productDto;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem save new Product");
        }

    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFound();
        }
    }
}
