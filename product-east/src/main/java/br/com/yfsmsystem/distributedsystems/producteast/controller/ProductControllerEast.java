package br.com.yfsmsystem.distributedsystems.producteast.controller;

import br.com.yfsmsystem.distributedsystems.producteast.dto.ProductDto;
import br.com.yfsmsystem.distributedsystems.producteast.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductControllerEast {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getListAllProductEast() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok().body(productService.getProductByName(name));
    }

    @PostMapping
    public ResponseEntity<URI> saveNewProduct(@RequestBody ProductDto productDto) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/find-name/{name}")
                .buildAndExpand(productService.saveNewProduct(productDto)
                        .getName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.saveNewProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
