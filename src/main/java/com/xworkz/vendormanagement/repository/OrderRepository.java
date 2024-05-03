package com.xworkz.vendormanagement.repository;

import com.xworkz.vendormanagement.entity.OrderEntity;

public interface OrderRepository {
	public boolean save(OrderEntity orderEntity);
}
