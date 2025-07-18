package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.BasketEntity;
import com.example.amagazishi.entity.ProductEntity;
import com.example.amagazishi.entity.UserEntity;
import com.example.amagazishi.repository.BasketRepository;
import com.example.amagazishi.service.AuthService;
import com.example.amagazishi.service.BasketService;
import com.example.amagazishi.service.ProductService;
import com.example.amagazishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final AuthService authService;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public BasketEntity addProductInBasket(Long id) {
        ProductEntity product = productService.getById(id);
        BasketEntity basket = basketRepository.findById(authService.getCurrentUser().getBasket().getId()).orElseThrow(() -> new RuntimeException("проблема с корзиной обратитесь к Администратору"));
        List<ProductEntity> productEntityList = basket.getProduct();
        productEntityList.add(product);
        basket.setProduct(productEntityList);
        return basketRepository.save(basket);
    }

    @Override
    public BasketEntity getBasket() {
        return basketRepository.findById(authService.getCurrentUser().getBasket().getId()).orElseThrow(() -> new RuntimeException("у вас нет корзины или проблема на части сервера"));
    }

    @Override
    public BasketEntity deleteProductId(Long id) {
        BasketEntity basket = basketRepository.findById(authService.getCurrentUser().getBasket().getId()).orElseThrow(() -> new RuntimeException("проблема с корзиной обратитесь к Администратору"));
        List<ProductEntity> productEntityList = basket.getProduct();
        productEntityList.remove(id);
        return basketRepository.save(basket);
    }

}
