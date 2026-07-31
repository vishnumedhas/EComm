package com.ecomm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.Role;
import com.ecomm.user.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(RoleType roleName);
}
