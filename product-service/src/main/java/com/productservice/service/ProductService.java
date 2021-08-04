package com.productservice.service;

import com.productservice.dto.ProductDto;
import com.productservice.repository.ProductRepository;
import com.productservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public Flux<ProductDto> getAll() {
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }


    public Mono<ProductDto> getProductById(final String id) {
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<ProductDto> insertProduct(final Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::insert)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<ProductDto> updateProduct(final String id, final Mono<ProductDto> productDtoMono) {
        return this.repository.findById(id)
                .flatMap(p -> productDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }


    public Mono<Void> deleteProductById(final String id) {
        return this.repository.deleteById(id);
    }
}
