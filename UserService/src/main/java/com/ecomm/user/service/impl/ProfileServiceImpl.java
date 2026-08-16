package com.ecomm.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.ProfileRepository;
import com.ecomm.user.request.ProfileUpdateRequest;
import com.ecomm.user.response.CloudinaryResponse;
import com.ecomm.user.service.CloudinaryService;
import com.ecomm.user.service.ProfileService;

@Service
public class ProfileServiceImpl  implements ProfileService{

	@Autowired
	private ProfileRepository prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CloudinaryService cservice;

	@Override
	public Profile addProfile(Profile profile) {
		
		return prepo.save(profile);
	}

	@Override
	public ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request,MultipartFile image) {
		Profile p=prepo.findById(profileId).orElseThrow(()->new AppException("USer not found", HttpStatus.NOT_FOUND));
		mapper.map(request, p);   //request has first name,last name so we are setting to the profile
		if(image!=null&& !image.isEmpty()) {
			if(p.getImageUrl()!=null&&p.getPublicUrl()!=null) {
				cservice.deleteImage(p.getPublicUrl());
			}
			CloudinaryResponse response=cservice.uploadImage(image);
			p.setImageUrl(response.getImageUrl());
			p.setPublicUrl(response.getPublicUrl());
		}
		prepo.save(p);
		return mapper.map(p, ProfileDto.class);
	}

	@Override
	public void deleteProfile(Integer profileId) {
		prepo.findById(profileId).orElseThrow(()->new AppException("User not found", HttpStatus.NOT_FOUND));
		prepo.deleteById(profileId);

	}

	@Override
	public ProfileDto getByProfileId(Integer profileId) {
		Profile p=prepo.findById(profileId).orElseThrow(()->new AppException("User not found", HttpStatus.NOT_FOUND));
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		return pdto;
	}

	@Override
	public ProfileDto getProfileByUserId(Integer userId) {
		Profile p=prepo.findByUserUserId(userId).orElseThrow(()->new AppException("User not found", HttpStatus.NOT_FOUND));
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		return pdto;
	}

}
