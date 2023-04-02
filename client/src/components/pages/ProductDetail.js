import { useState, useEffect } from 'react';
import { ProductPage } from '../organism/ProductDetail';
import { useParams } from 'react-router-dom';

const ProductDetail = () => {
  const [productData, setProductData] = useState();
  const [eatableVegetarianList, setEatableVegetarainList] = useState();
  const { id } = useParams();
  const getData = async () => {
    let url = `${process.env.REACT_APP_API_URL}/products/detail/${id}`;

    try {
      const data = await fetch(url).then(res => {
        if (!res.ok) throw new Error('No Response');
        return res.json();
      });
      setProductData(data.product);
      const eatableType = (data.eatableVegetarain).join(', ');

      setEatableVegetarainList(eatableType);
    } catch (e) {
      console.log(e.message);
    }
  };
  useEffect(() => {
    getData();
  }, []);

  return <ProductPage productId={id} productData={productData} eatableVegetarianList={eatableVegetarianList}/>;
};
export default ProductDetail;
