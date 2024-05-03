package com.xworkz.vendormanagement.service;

import com.xworkz.vendormanagement.dto.OrderDTO;

public interface OrderService {
	public boolean validateAndSave(OrderDTO orderDTO);

}
