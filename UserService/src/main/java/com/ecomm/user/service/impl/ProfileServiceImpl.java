package com.ecomm.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.entity.Profile;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.ProfileRepository;
import com.ecomm.user.service.ProfileService;

@Service
public class ProfileServiceImpl  implements ProfileService{

	@Autowired
	private ProfileRepository prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public Profile addProfile(Profile profile) {
		// TODO Auto-generated method stub
		profile=prepo.save(profile);
		return profile;
	}

	@Override
	public void updateProfile(Integer profileId,Profile profile) {
		// TODO Auto-generated method stub
		Profile p=prepo.findById(profileId).orElse(null);
		if(p==null) {
			throw new AppException("Profile not found!", HttpStatus.NOT_FOUND);
		}
		else {
			prepo.save(profile);
		}
		
	}

	@Override
	public void deleteProfile(Integer profileId) {
		// TODO Auto-generated method stub
		Profile p =prepo.findById(profileId).orElse(null);
		if(p==null) {
			throw new AppException("User not found!", HttpStatus.NOT_FOUND);
		}
		else {
			prepo.deleteById(profileId);
		}
	}

	@Override
	public Profile findById(Integer profileId) {
		// TODO Auto-generated method stub
		Profile p =prepo.findById(profileId).orElse(null);
		return p;
	}


	@Override
	public Profile getProfileByUserId(Integer userId) {
		Profile p=prepo.findByUserUserId(userId).orElseThrow(()->new AppException("Profile Not Found!", HttpStatus.NOT_FOUND));
		return p;
	}

}
