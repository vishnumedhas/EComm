package com.dcl.service;

import com.dcl.req.dto.AddRequest;
import com.dcl.response.dto.ProductDto;

public interface ProductService {

	ProductDto addProduct(AddRequest request);
}
