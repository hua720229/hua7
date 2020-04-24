package com.lt.service;

import java.util.List;

import com.lt.pojo.Cart;

//添加Cart接口
public interface DubboCartService {

	List<Cart> findCartListByUserId(Long userId);

	void updateCartNum(Cart cart);

	void deleteCart(Cart cart);

	void insertCart(Cart cart);

}
