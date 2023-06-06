package com.noterror.app.api.admin;

import com.noterror.app.api.product.dto.ProductRequestDto;
import com.noterror.app.api.product.dto.ProductResponseDto;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.global.response.SingleProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * SCOPE : ADMIN PAGE, ROLE_ADMIN
 */
@Api(tags = {"BACK-OFFICE", "PRODUCT"})
@RestController
@Validated
@Slf4j
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    /**
     * 제품 등록
     *
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @ApiOperation(value = "제품 추가 API", notes = "입력한 정보에 맞는 제품을 추가합니다.")
    @PostMapping("/registration")
    public ResponseEntity<ProductResponseDto> postProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        Product newProduct = productService.createProduct(product);
        ProductResponseDto response = new ProductResponseDto(newProduct);

        return new ResponseEntity(
                new SingleProductResponse(response), HttpStatus.CREATED);
    }

    /**
     * 제품 수정
     *
     * @PathVariable 제품 식별자
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @ApiOperation(value = "제품 정보 수정 API", notes = "제품 Id에 해당하는 제품의 정보를 수정합니다.")
    @PutMapping("/edit/{product-id}")
    public ResponseEntity<SingleProductResponse<Product>> putProduct(@ApiParam(value = "제품 ID", required = true, example = "1") @PathVariable("product-id") Long productId,
                                                               @ApiParam(value = "수정 정보") @Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        Product updateProduct = productService.updateProduct(productId, product);
        ProductResponseDto response = new ProductResponseDto(updateProduct);

        return new ResponseEntity<>(
                new SingleProductResponse(response), HttpStatus.OK);
    }

    /**
     * 제품 삭제
     */
    @ApiOperation(value = "제품 삭제 API", notes = "제품 Id에 해당하는 제품을 삭제합니다.")
    @ApiResponse(code = 204, message = "No content")
    @DeleteMapping("/edit/{product-id}")
    public ResponseEntity deleteProduct(@ApiParam(value = "제품 ID", required = true, example = "1") @PathVariable("product-id") Long productId) {
        productService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
