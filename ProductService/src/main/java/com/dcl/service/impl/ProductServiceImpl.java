package com.dcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcl.entity.Product;
import com.dcl.repo.ProductRepository;
import com.dcl.req.dto.AddRequest;
import com.dcl.response.dto.ProductDto;
import com.dcl.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ProductDto addProduct(AddRequest request) {
		
		Product p=mapper.map(request, Product.class);
		p=prepo.save(p);
		ProductDto dto= mapper.map(p, ProductDto.class);
		
		return dto;
	}

}
