import React, { useState } from 'react';
import * as Styled from './Style';

const CartItem = ({ el, deleteCartItem, fixCartItemQuantity }) => {
  const { productName, price, thumbnailImage, stockQuantity} = el.product;
  const { purchaseQuantity, cartDetailId} = el;
  const [totalPrice, setTotalPrice] = useState(price * purchaseQuantity);

  const deleteItem = () => {
    deleteCartItem(cartDetailId);
  };

  const changeQuantity = e => {
    setTotalPrice(e.target.value * price);
  };

  const fixQuantity = e => {
    e.preventDefault();
    fixCartItemQuantity(cartDetailId, e.target[0].value);
  };

  return (
    <Styled.ItemWrapper>
      <Styled.ImgBox>
        <img src={thumbnailImage} alt="제품이미지" />
      </Styled.ImgBox>
      <Styled.InfoBox>
        <Styled.ItemName>{productName}</Styled.ItemName>
        
        <form onSubmit={e => fixQuantity(e)}>
        {stockQuantity === 0
        ? (<Styled.SoldOut>품절</Styled.SoldOut>) 
        : (<Styled.ItemQuantity
          defaultValue={purchaseQuantity}
          type="number"
          onChange={changeQuantity}
        />)}
          {stockQuantity === 0
        ? (<div></div>) 
        : (<button>수량 저장하기</button>)}  
        </form>

        <Styled.ItemPrice> 개별 금액 : {price} 원 </Styled.ItemPrice>
        <Styled.TotalPrice> 총 금액 : {totalPrice} 원</Styled.TotalPrice>
        {/* 수량은 버튼식으로 조정할 것 */}
      </Styled.InfoBox>
      <button onClick={deleteItem}>삭제하기</button>
    </Styled.ItemWrapper>
  );
};

export default CartItem;
