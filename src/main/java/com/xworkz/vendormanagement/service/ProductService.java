package com.xworkz.vendormanagement.service;

import java.util.List;

import com.xworkz.vendormanagement.dto.ProductDTO;

public interface ProductService {
	public boolean validateAndSave(ProductDTO addProductDTO);
	
	List<ProductDTO> getAllProducts();
	
	List<ProductDTO> getAllProductDetailsByVendorId(String email);

	//update	
    ProductDTO findAllProductEntityById(int id) ;
	
	ProductDTO updateProductById(ProductDTO productDTO, int id);

	
}
