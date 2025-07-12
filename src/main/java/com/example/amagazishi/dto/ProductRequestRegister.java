package com.example.amagazishi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@With
@Getter
@Setter
@Builder
public class ProductRequestRegister {
    private Long price;
    private String title;
    private String compound;
    private Integer discount;
    private String description;
}
