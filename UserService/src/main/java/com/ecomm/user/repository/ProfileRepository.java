package com.ecomm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
	
//	@Query update table Profile set 
//	Profile updateProfile(Integer ProfileId,Profile profile);

}
