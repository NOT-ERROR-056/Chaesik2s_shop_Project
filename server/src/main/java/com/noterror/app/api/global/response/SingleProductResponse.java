package com.noterror.app.api.global.response;

import com.noterror.app.api.entity.Vegetarian;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SingleProductResponse<T> {

    @ApiModelProperty(notes = "제품 정보")
    private T product;

    @ApiModelProperty(value = "섭취 가능한 유형 리스트", dataType = "list")
    private List<String> eatableVegetarian;

    public SingleProductResponse(T product, List<Vegetarian> eatableVegetarian) {
        this.product = product;
        this.eatableVegetarian = eatableVegetarian.stream()
                .map(Vegetarian::getVegetarianType)
                .collect(Collectors.toList());
    }

    public SingleProductResponse(T product) {
        this.product = product;
    }

}
