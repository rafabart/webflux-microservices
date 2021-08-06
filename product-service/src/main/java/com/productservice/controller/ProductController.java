package com.productservice.controller;

import com.productservice.dto.ProductDto;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    final private ProductService productService;


    @GetMapping
    public Flux<ProductDto> getAll() {
        return this.productService.getAll();
    }


    @GetMapping("price-range")
    public Flux<ProductDto> getByPriceRange(@RequestParam("min") int min,
                                            @RequestParam("max") int max) {
        return this.productService.getProductByPriceRange(min, max);
    }


    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable("id") final String id) {
        return this.productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return this.productService.insertProduct(productDtoMono);
    }


    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> insertProduct(@PathVariable("id") final String id,
                                                          @RequestBody Mono<ProductDto> productDtoMono) {
        return this.productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") final String id) {
        return this.productService.deleteProductById(id);
    }
}
