package com.noterror.app.api.domain.product.dto;

import com.noterror.app.api.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
    private String thumbnailImage;
    private  String detailImage;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.quantity = product.getStockQuantity();
        this.price = product.getPrice();
        this.createDate = product.getCreateDate();
        this.editDate = product.getEditDate();
    }
}
