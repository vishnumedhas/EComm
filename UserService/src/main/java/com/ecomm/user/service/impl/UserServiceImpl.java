package com.ecomm.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.dto.UserDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.entity.Role;
import com.ecomm.user.entity.User;
import com.ecomm.user.enums.RoleType;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.request.UpdateRequest;
import com.ecomm.user.service.ProfileService;
import com.ecomm.user.service.RoleService;
import com.ecomm.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ProfileService pservice;
	
	@Autowired
	private RoleService rservice;
	
	
	@Transactional
	@Override
	public UserDto register(RegisterRequest request) {
		
		User alreadyExists=urepo.findByEmail(request.getEmail()).orElse(null);
		
		if(alreadyExists!=null) {
			throw new AppException("User already exists",HttpStatus.BAD_REQUEST);
		}
		User u=mapper.map(request, User.class);
		Profile p=mapper.map(request, Profile.class);
		RoleDto rdto=rservice.getRoleByRoleName(request.getRoleName());
		Role r=mapper.map(rdto, Role.class);
		u.setRole(r);
		
		u=urepo.save(u);
		p.setUser(u);
		p=pservice.addProfile(p);
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		UserDto dto=mapper.map(u, UserDto.class);
		dto.setProfileDto(pdto);
		dto.setRoleDto(rdto);
		return dto;
	}

	@Override
	public UserDto login(LoginRequest request) {
		// TODO Auto-generated method stub
	User alreadyExists=	urepo.findByEmail(request.getEmail()).orElse(null);
	
	if(alreadyExists==null) {
		throw new AppException("User not found!",HttpStatus.NOT_FOUND );
	}
	
	if(!request.getPassword().equals(alreadyExists.getPassword())) {
		throw new AppException("Incorrect Password!", HttpStatus.BAD_REQUEST);
	}
	
	UserDto dto=mapper.map(alreadyExists, UserDto.class);
	ProfileDto pdto=mapper.map(pservice.getProfileByUserId(alreadyExists.getUserId()), ProfileDto.class);
	dto.setRoleDto(mapper.map(alreadyExists.getRole(),RoleDto.class));
	
	dto.setProfileDto(pdto);
		return dto;
	}

	@Override
	public UserDto getById(Integer userId) {
		// TODO Auto-generated method stub
		User user = urepo.findById(userId).orElse(null);

	    if(user == null) {
	        throw new AppException("User not found!", HttpStatus.NOT_FOUND);
	    }

	    UserDto dto = mapper.map(user, UserDto.class);

	    return dto;
	}

	@Override
	public List<UserDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Integer userId, UpdateRequest request) {
		

	}

	@Override
	public void deleteById(Integer userId) {
		// TODO Auto-generated method stub

	}

}






