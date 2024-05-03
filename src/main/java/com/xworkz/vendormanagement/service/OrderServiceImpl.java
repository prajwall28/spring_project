package com.xworkz.vendormanagement.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.dto.OrderDTO;
import com.xworkz.vendormanagement.entity.OrderEntity;
import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.repository.OrderRepository;
import com.xworkz.vendormanagement.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean validateAndSave(OrderDTO orderDTO) {
		log.info("Invoking validateAndSave....");
		 ProductEntity productEntity = productRepository.getAllProductsById(orderDTO.getProductId());
		    System.out.println("productEntity=======" + productEntity);
		    
		    if (productEntity != null) {
		        OrderEntity orderEntity = new OrderEntity();
		       	        
		        orderDTO.setProductName(productEntity.getProductName());
		        orderDTO.setCategory(productEntity.getCategory());
		        orderDTO.setProductPrice(productEntity.getProductPrice());
		        orderDTO.setDeliveryCharge(productEntity.getDeliveryCharge());
		        orderDTO.setDescription(productEntity.getDescription());
		        orderDTO.setAvailable(productEntity.getAvailable());
		        orderDTO.setOrderDate(LocalDateTime.now());
		        orderDTO.setVendor(productEntity.getVendor());
		        orderDTO.setProduct(productEntity);
		        
		        BeanUtils.copyProperties(orderDTO, orderEntity);
		        
		        boolean save = orderRepository.save(orderEntity);
		        if (save) {
		            log.info("Data saved Successfully");
		            return true;
		        }
		    } else {
		        log.error("Product details not found for productId: " + orderDTO.getProductId());
		    }
		    
		    return false;
		}
}
