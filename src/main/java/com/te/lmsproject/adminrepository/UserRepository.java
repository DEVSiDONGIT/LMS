package com.te.lmsproject.adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lmsproject.adminentity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);

}
