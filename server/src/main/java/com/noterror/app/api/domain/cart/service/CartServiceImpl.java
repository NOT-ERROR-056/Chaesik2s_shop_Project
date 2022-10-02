package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public void createCart(Member member) {
        Cart cart = Cart.createCart(member);
        cartRepository.save(cart);
    }

    @Transactional
    public CartDetailDto addCart(CartProductDto cartProductDto, Long memberId) {

        Member member= memberRepository.findById(memberId).get();
        Product product = productRepository.findById(cartProductDto.getProductId()).get();
        Cart cart = member.getCart();

        if( cart == null ){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        //상품이 장바구니에 있는지 없는지 조회
        CartDetail productsByCartId = cartDetailRepository.findByCartIdAndProductId(cart.getCartId(), product.getProductId());

        // 만약 상품이 이미 있으면은 개수를 +
        if( productsByCartId != null) {
            productsByCartId.addCount(cartProductDto.getCount());
            cartDetailRepository.save(productsByCartId);
            CartDetailDto cartDetailDto = new CartDetailDto();
            cartDetailDto.setCartDetailId(productsByCartId.getCartDetailId());
            cartDetailDto.setProductName(productsByCartId.getProduct().getProductName());
            cartDetailDto.setPrice(productsByCartId.getProduct().getPrice());
            cartDetailDto.setCount(productsByCartId.getCount());
            return cartDetailDto;
        } else { // 아니면은 CartItem 에 상품 저장
            CartDetail cartItem = CartDetail.createCartDetail(cart, product, cartProductDto.getCount());
            cartDetailRepository.save(cartItem);
            CartDetailDto cartDetailDto = new CartDetailDto();
            cartDetailDto.setCartDetailId(cartItem.getCartDetailId());
            cartDetailDto.setProductName(cartItem.getProduct().getProductName());
            cartDetailDto.setPrice(cartItem.getProduct().getPrice());
            cartDetailDto.setCount(cartItem.getCount());
            return cartDetailDto;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartDetailDto> listCart(Long memberId) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findById(memberId).get();
        Cart cart = member.getCart();

        if(cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartDetailRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDtoList;
    }

    //장바구니 상품수량 up
    @Override
    public CartProductDto updateCart(Long cartDetailId, int count){
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).get();
        cartDetail.updateCount(count);
        cartDetailRepository.save(cartDetail);
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setProductId(cartDetail.getProduct().getProductId());
        cartProductDto.setCount(cartDetail.getCount());
        return cartProductDto;
    }

    @Override
    public void deleteCart(Long cartDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).get();
        cartDetailRepository.delete(cartDetail);
    }

    @Override
    public void deleteCartAll(Long memberId) {
        List<CartDetail> products = cartDetailRepository.findAll();
        //해당하는 유저의 장바구니 내역 삭제
        for(CartDetail cartDetail : products){
            if(cartDetail.getCart().getMember().getMemberId() == memberId ){
                cartDetail.getCart().setCount(cartDetail.getCart().getCount() - 1);
                cartDetailRepository.deleteById(cartDetail.getCartDetailId());
            }
        }
    }
}