package com.dcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.req.dto.AddRequest;
import com.dcl.response.ApiResponse;
import com.dcl.response.dto.ProductDto;
import com.dcl.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;

	@PostMapping("/add")
	public ResponseEntity<?>addProduct(@RequestBody AddRequest request){
		ProductDto dto=pservice.addProduct(request);
		return new ResponseEntity<>(new ApiResponse<>("Product Added Successfully",dto,HttpStatus.OK),HttpStatus.OK);
	}
}
