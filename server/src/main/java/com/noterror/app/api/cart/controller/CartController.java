package com.noterror.app.api.cart.controller;

import com.noterror.app.api.cart.dto.CartDetailResponseDto;
import com.noterror.app.api.cart.service.CartService;
import com.noterror.app.api.cart.dto.AddProductInCartDto;
import com.noterror.app.api.cart.dto.CartDetailUpdateInfoDto;
import com.noterror.app.api.cart.dto.CartResponseDto;
import com.noterror.app.api.member.service.MemberService;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.response.SingleCartDetailResponse;
import com.noterror.app.api.global.response.SingleCartResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"FRONT-OFFICE", "CART"})
@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    /**
     * 장바구니 제품 추가
     */
    @ApiOperation(value = "장바구니 제품 추가 API", notes = "제품 ID에 해당하는 제품을 장바구니에 추가합니다.")
    @PostMapping("/product/{product-id}")
    public ResponseEntity<SingleCartDetailResponse<CartDetail>> addProductInCart(@ApiParam(value = "장바구니 ID", required = true, example = "1") @PathVariable("product-id") Long productId,
                                           @ApiParam(value = "선택한 제품의 수량") @RequestBody @Valid AddProductInCartDto addProductInCartDto) {
        CartDetail cartDetail = toCartDetailByProduct(productId, addProductInCartDto);
        Cart cart = cartService.addProductInCart(cartDetail);
        CartDetailResponseDto response = new CartDetailResponseDto(cart);

        return new ResponseEntity(
                new SingleCartDetailResponse(response), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 전체 조회
     */
    @ApiOperation(value = "장바구니에 제품 전체 조회", notes = "장바구니 내역을 전체 조회합니다.")
    @GetMapping
    public ResponseEntity<SingleCartResponse<Cart>> getCartDetailsInCart() {
        Cart cartOfMember = cartService.getCartOfMember(getMemberByEmail());
        CartResponseDto response = new CartResponseDto(cartOfMember);
        return new ResponseEntity(
                new SingleCartResponse(response), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 수량 변경
     */
    @ApiOperation(value = "장바구니에 제품 수량 변경", notes = "장바구니에 제품 ID에 해당하는 수량을 변경합니다.")
    @PutMapping("/cart-detail/{cart-detail-id}")
    public ResponseEntity<SingleCartDetailResponse<CartDetail>> updateCartProduct(@ApiParam(value = "장바구니에 제품 ID", required = true, example = "1") @PathVariable("cart-detail-id") Long cartDetailId,
                                            @ApiParam(value = "변경 수량") @RequestBody @Valid CartDetailUpdateInfoDto cartDetailUpdateInfoDto) {
        cartDetailUpdateInfoDto.setCartDetailId(cartDetailId);
        Cart updateCart = cartService.updateCartDetail(cartDetailUpdateInfoDto);
        CartDetailResponseDto response = new CartDetailResponseDto(updateCart);
        return new ResponseEntity(
                new SingleCartDetailResponse(response), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 삭제
     */
    @ApiOperation(value = "장바구니에 제품 삭제", notes = "장바구니에 제품 ID에 해당하는 제품을 삭제합니다.")
    @DeleteMapping("cart-detail/{cart-detail-id}")
    public ResponseEntity deleteCartProduct(@ApiParam(value = "장바구니에 제품 ID", required = true, example = "1") @PathVariable("cart-detail-id") Long cartDetailId) {
        cartService.deleteCart(cartDetailId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Member getMemberByEmail() {
        return memberService.findMemberByEmail(getCurrentUserEmail());
    }

    private CartDetail toCartDetailByProduct(Long productId, AddProductInCartDto addProductInCartDto) {
        return addProductInCartDto
                .toCartDetailWithMemberAndProduct
                        (getMemberByEmail(), getProduct(productId));
    }

    private Product getProduct(Long productId) {
        return productService.findProduct(productId);
    }
}