package br.com.yfsmsystem.distributedsystems.productcentral.controller;

import br.com.yfsmsystem.distributedsystems.productcentral.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.productcentral.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/central")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductbyId(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveNewProduct(@RequestBody  ProductDto productDto){
        return ResponseEntity.ok(productService.saveNewProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(id,productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
