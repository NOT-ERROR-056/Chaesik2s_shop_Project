package com.noterror.app.api.orders.controller;

import com.noterror.app.api.cart.service.CartService;
import com.noterror.app.api.member.service.MemberService;
import com.noterror.app.api.orders.dto.OrderDetailDto;
import com.noterror.app.api.orders.dto.OrderResponseDto;
import com.noterror.app.api.orders.service.OrdersService;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.OrderDetail;
import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import com.noterror.app.api.global.response.SingleOrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"FRONT-OFFICE", "ORDER"})
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrdersController {

    private final OrdersService ordersService;
    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    /**
     * 주문 내역 조회
     * 페이지네이션 기능 추가
     */
    @ApiOperation(value = "주문 내역 조회 API", notes = "주문 내역 리스트를 출력합니다.")
    @GetMapping("/list")
    public ResponseEntity<MultiOrdersResponse> getOrderList(@RequestParam(required = false, defaultValue = "1") int page,
                                                            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<Orders> ordersInPage = ordersService.getOrderList(getMemberByEmail(), page, size);
        List<OrderResponseDto> response = toListOfOrderResponses(ordersInPage);
        return new ResponseEntity(
                new MultiOrdersResponse(response, ordersInPage), HttpStatus.OK);
    }

    /**
     * 단일 주문
     */
    @ApiOperation(value = "단일 주문 API", notes = "단일 제품을 주문합니다.")
    @PostMapping("/product/{product-id}")
    public ResponseEntity<SingleOrderResponse<Orders>> OrderSingleProduct(
            @ApiParam(value = "제품 ID", required = true, example = "1") @PathVariable("product-id") Long productId,
            @RequestBody @Valid OrderDetailDto orderDetailDto) {
        OrderDetail currentOrderProductInfo = getOrderDetail(productId, orderDetailDto);
        Orders singleOrder = setOrderInfo(currentOrderProductInfo);
        Orders newOrder = ordersService.orderProduct(singleOrder);
        OrderResponseDto response = new OrderResponseDto(newOrder);
        return new ResponseEntity(
                new SingleOrderResponse<>(response), HttpStatus.OK);
    }

    /**
     * 장바구니 내역 전체 주문
     */
    @ApiOperation(value = "장바구니 전체 주문 API", notes = "장바구니에 제품 리스트 전체를 주문합니다.")
    @PostMapping("/cart")
    public ResponseEntity<SingleOrderResponse<Orders>> orderProductsInCart() {
        Cart cartOfMember = getCartByMember();
        Orders newOrder = ordersService.orderProductsInCart(cartOfMember);

        // 장바구니에 제품 주문이 성공하면, 장바구니 내역 전체 삭제 기능이 수행
        cartService.successOrderAndDeleteCartAll(cartOfMember);

        OrderResponseDto response = new OrderResponseDto(newOrder);
        return new ResponseEntity(
                new SingleOrderResponse(response), HttpStatus.OK);
    }

    /**
     * TODO 주문 취소
     */
    @DeleteMapping("/{orders-id}")
    public ResponseEntity deleteOrder(@PathVariable("orders-id") Long ordersId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Member getMemberByEmail() {
        return memberService.findMemberByEmail(getCurrentUserEmail());
    }

    private Product getProduct(Long productId) {
        return productService.findProduct(productId);
    }

    private OrderDetail getOrderDetail(Long productId, OrderDetailDto orderDetailDto) {
        return orderDetailDto.toOrderDetailWithProduct(
                getProduct(productId)
        );
    }

    private Orders setOrderInfo(OrderDetail currentOrderProductInfo) {
        return currentOrderProductInfo.toOrdersWithMember(getMemberByEmail());
    }

    private Cart getCartByMember() {
        return getMemberByEmail().getCart();
    }

    private List<OrderResponseDto> toListOfOrderResponses(Page<Orders> ordersInPage) {
        return ordersInPage.stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

}
