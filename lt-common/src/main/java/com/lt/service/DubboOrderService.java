package com.lt.service;

import com.lt.pojo.Order;

public interface DubboOrderService {

	String insterOrder(Order order);

	Order findOrderById(String id);

}
