package br.com.yfsmsystem.distributedsystems.productcentral.service;


import br.com.yfsmsystem.distributedsystems.productcentral.convert.ProductConvert;
import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.entity.Product;
import br.com.yfsmsystem.distributedsystems.productcentral.exception.ProductNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.integration.RabbitIntegration;
import br.com.yfsmsystem.distributedsystems.productcentral.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RabbitIntegration rabbitIntegration;

    public ProductDto getProductById(Long id) {
        return ProductConvert.mapEntityToDto(
                productRepository.findById(id)
                        .orElseThrow(ProductNotFound::new));
    }

    public List<ProductDto> getAllProducts() {
        return ProductConvert.mapListEntityToListDto(
                productRepository.findAll());
    }


    public ProductDto saveNewProduct(ProductDto productDto) {
        try {
            Product productSaved = productRepository.save(ProductConvert.mapDtoToEntity(productDto));
            rabbitIntegration.sendProductForBranchs(ProductConvert.mapEntityToDto(productSaved));
            return ProductConvert.mapEntityToDto(productSaved);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problem save new Product");
        }

    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        if (productRepository.existsById(id)) {
            return ProductConvert
                    .mapEntityToDto(productRepository
                            .save(ProductConvert
                                    .mapDtoToEntity(productDto)));
        } else {
            throw new ProductNotFound();
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
