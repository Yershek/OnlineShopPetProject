package com.example.amagazishi.controller;

import com.example.amagazishi.dto.BasketResponse;
import com.example.amagazishi.entity.BasketEntity;
import com.example.amagazishi.entity.ProductEntity;
import com.example.amagazishi.mapper.BasketMapper;
import com.example.amagazishi.service.BasketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baskets")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/add-product-in-basket/{id}")
    public ResponseEntity<String> addProductInBasket(@PathVariable("id") Long id) {
        basketService.addProductInBasket(id);
        return ResponseEntity.ok("Product in basket added");
    }

    @GetMapping("/get-basket")
    public ResponseEntity<BasketResponse> getBasket() {
        return ResponseEntity.ok(BasketMapper.toBasketResponse(basketService.getBasket()));
    }

    @PostMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProductInBasket(@PathVariable("id") Long id ) {
        basketService.deleteProductId(id);
        return ResponseEntity.ok().body("Product in basket removed");
    }

}
