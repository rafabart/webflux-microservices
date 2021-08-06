package com.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String description;
    private Integer price;


    public ProductDto(final String description, final Integer price) {
        this.description = description;
        this.price = price;
    }
}
