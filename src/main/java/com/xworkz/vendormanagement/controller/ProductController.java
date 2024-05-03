package com.xworkz.vendormanagement.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.vendormanagement.dto.ProductDTO;
import com.xworkz.vendormanagement.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveProduct")
	public String showAddProductPage(@Valid ProductDTO dto, Model model) {
		log.info("Invoking showAddProductPage.....");
		boolean save=this.productService.validateAndSave(dto);
		if(save) {
			return "profile";
		}
		return "success";
	}
	
	@PostMapping("/getproductDetails")
	public String readProduct(@RequestParam String email,Model model) {
		System.err.println("email============================"+email);
		List<ProductDTO> dto=productService.getAllProductDetailsByVendorId(email);
		model.addAttribute("products", dto);
		return "profile";
		
	}
	///update
	@PostMapping("/updateProduct")
	public String updateProductPage(@RequestParam int id, Model model) {
	    try {
	        System.out.println("Id=========================" + id);
	        ProductDTO productDTO = productService.findAllProductEntityById(id);
	        model.addAttribute("product", productDTO);
	        return "updateProduct";
	    } catch (EntityNotFoundException e) {
	        model.addAttribute("errorMessage", "Product not found for Id: " + id);
	        return "errorPage"; 
	    }
	}

	
	@PostMapping("/update")
	public String update(ProductDTO dto, Model model, int id, HttpSession session) {
	    System.out.println("" + id);
	    System.out.println("dto============================" + dto.getId());
	    session.setAttribute("Id", id);
	    System.out.println(id);
	    
	    ProductDTO update = productService.updateProductById(dto, dto.getId());
	    System.out.println("============================================="+update);
	    if (update != null) { 
	        ProductDTO read = productService.findAllProductEntityById(update.getId());
	        model.addAttribute("readSave", read);
	        model.addAttribute("message", "Data updated successfully");
	        return "updateProduct";
	    } else {
	        model.addAttribute("errorMessage", "Product not found for Id: " + id);
	        return "updateProduct"; 
	    }
	}

}
