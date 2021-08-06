package com.productservice.service;

import com.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

    private final ProductService productService;


    @Override
    public void run(String... args) throws Exception {

        Flux.just(
                        new ProductDto("4k-TV", 1000),
                        new ProductDto("slr-camera", 750),
                        new ProductDto("iphone", 800),
                        new ProductDto("headphone", 100)
                )
                .flatMap(product -> this.productService.insertProduct(Mono.just(product)))
                .subscribe(p -> log.info("Product={}", p));

    }
}
