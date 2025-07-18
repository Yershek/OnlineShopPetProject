package com.example.amagazishi.controller;

import com.example.amagazishi.dto.ProductRequestRegister;
import com.example.amagazishi.dto.ProductRequestUpdate;
import com.example.amagazishi.dto.ProductResponse;
import com.example.amagazishi.dto.ProductResponseMini;
import com.example.amagazishi.mapper.ProductMapper;
import com.example.amagazishi.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-product-mini-containing")
    public ResponseEntity<List<ProductResponseMini>> getProductMiniContaining(String title) {
        return ResponseEntity.ok(productService.findByTitleContaining(title).stream().map(ProductMapper::toProductResponseMini).toList());
    }

    @GetMapping("/get-product-containing")
    public ResponseEntity<List<ProductResponse>> getProductContaining(String title) {
        return ResponseEntity.ok(productService.findByTitleContaining(title).stream().map(ProductMapper::toProductResponse).toList());
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequestRegister  productRequestRegister){
        return ResponseEntity.ok(ProductMapper.toProductResponse(productService.update(productService.save(ProductMapper.toProductEntity(productRequestRegister)))));
    }

    @PutMapping("/update-product")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequestUpdate  productRequestUpdate){
        return ResponseEntity.ok(ProductMapper.toProductResponse(productService.update(ProductMapper.toProductEntityUpdate(productRequestUpdate))));
    }

    @GetMapping("/get-product-by-id")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam Long id){
        return ResponseEntity.ok(ProductMapper.toProductResponse(productService.getById(id)));
    }

    @GetMapping("/get-product-mini-by-id")
    public ResponseEntity<ProductResponseMini> getProductMiniById(@RequestParam Long id){
        return ResponseEntity.ok(ProductMapper.toProductResponseMini(productService.getById(id)));
    }

    @GetMapping("/get-all-product")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(productService.getAll().stream().map(ProductMapper::toProductResponse).toList());
    }

    @GetMapping("/get-all-product-mini")
    public ResponseEntity<List<ProductResponseMini>> getAllProductMini(){
        return ResponseEntity.ok(productService.getAll().stream().map(ProductMapper::toProductResponseMini).toList());
    }

}