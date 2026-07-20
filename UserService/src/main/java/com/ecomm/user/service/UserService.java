package com.ecomm.user.service;



import java.util.List;

import com.ecomm.user.dto.UserDto;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.request.UpdateRequest;

public interface UserService {

	UserDto register(RegisterRequest request);
	
	UserDto login(LoginRequest request);
	
	UserDto getById(Integer userId);
	
	List<UserDto> getAll();
	
	void updateUser(Integer userId, UpdateRequest request);
	
	void deleteById(Integer userId);
	
	
	
	
	
	
	
}
