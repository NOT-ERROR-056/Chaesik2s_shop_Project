package com.noterror.app.api.global.response;

import com.noterror.app.api.entity.Vegetarian;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SingleProductResponse<T> {

    private T product;
    private List<String> eatableVegetarain;

    public SingleProductResponse(T product, List<Vegetarian> eatableVegetarian) {
        this.product = product;
        this.eatableVegetarain = eatableVegetarian.stream()
                .map(v -> v.getVegetarianType())
                .collect(Collectors.toList());
    }

    public SingleProductResponse(T product) {
        this.product = product;
    }

}
