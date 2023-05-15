package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ems.entity.User;
import com.ems.serviceImpl.user;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u from User u where u.username= ?1")
	public User findByUserName(String username);

	public User save(user user);

}



