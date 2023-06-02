package com.noterror.app.api.global.response;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.Vegetarian;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SingleProductResponse<T> {

    private T product;

    @ApiModelProperty(notes = "제품을 섭취할 수 있는 유형", example = "비건")
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
