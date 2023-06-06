package com.noterror.app.api.product.dto;

import com.noterror.app.api.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    @ApiModelProperty(notes = "제품 식별자", example = "1")
    private Long productId;

    @ApiModelProperty(notes = "제품명", example = "제품명")
    private String productName;

    @ApiModelProperty(notes = "재고수량", example = "5")
    private int StockQuantity;

    @ApiModelProperty(notes = "제품 가격", example = "10000")
    private int price;

    @ApiModelProperty(notes = "제품 등록 날짜")
    private LocalDateTime createDate;

    @ApiModelProperty(notes = "제품 수정 날짜")
    private LocalDateTime editDate;

    @ApiModelProperty(notes = "썸네일 이미지")
    private String thumbnailImage;

    @ApiModelProperty(notes = "상세 정보 이미지")
    private String detailImage;

    @ApiModelProperty(notes = "섭취 가능 최상위 유형", example = "비건")
    private String vegetarianType;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.StockQuantity = product.getStockQuantity();
        this.price = product.getPrice();
        this.createDate = product.getCreateDate();
        this.editDate = product.getEditDate();
        this.thumbnailImage = product.getThumbnailImage();
        this.detailImage = product.getDetailImage();
        this.vegetarianType = product.getVegetarianType();
    }
}
