package com.example.amagazishi.controller;

import com.example.amagazishi.entity.BasketEntity;
import com.example.amagazishi.entity.ProductEntity;
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

    @PostMapping("/add-product-in-basket")
    public ResponseEntity<String> addProductInBasket(@RequestBody ProductEntity entity) {
        basketService.addProductInBasket(entity);
        return ResponseEntity.ok("Product in basket added");
    }

    @GetMapping("/get-basket")
    public ResponseEntity<BasketEntity> getBasket() {
        return ResponseEntity.ok(basketService.getBasket());
    }

    @PostMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProductInBasket(@PathVariable("id") Long id ) {
        basketService.deleteProductId(id);
        return ResponseEntity.ok().body("Product in basket removed");
    }

}
