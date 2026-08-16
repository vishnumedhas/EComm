package com.dcl.req.dto;

import lombok.Data;

@Data
public class AddRequest {

	private String productName;
	
	private Double productPrice;
	
	private String description;
}
