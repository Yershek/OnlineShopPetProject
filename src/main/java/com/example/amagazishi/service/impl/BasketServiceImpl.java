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

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final AuthService authService;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public BasketEntity addProductInBasket(ProductEntity product) {
        if(!basketRepository.existsById(authService.getCurrentUser().getBasket().getId())) {
            BasketEntity basket = new BasketEntity().addProduct(product);
            UserEntity user = authService.getCurrentUser();
            user.setBasket(basket);
            userService.update(user);
            return basketRepository.save(basket);
        }
        BasketEntity basket = basketRepository.findById(authService.getCurrentUser().getBasket().getId()).orElseThrow(() -> new RuntimeException("проблема с корзиной обратитесь к Администратору"));
        basket.addProduct(product);
        return basketRepository.save(basket);
    }

    @Override
    public BasketEntity getBasket() {
        return basketRepository.findById(authService.getCurrentUser().getBasket().getId()).orElseThrow(() -> new RuntimeException("у вас нет корзины или проблема на части сервера"));
    }

    @Override
    public BasketEntity deleteProductId(Long id) {
        BasketEntity basket = basketRepository.findById(authService.getCurrentUser().getBasket().getId()).get().removeProduct(productService.getById(id));
        return basketRepository.save(basket);
    }

}
