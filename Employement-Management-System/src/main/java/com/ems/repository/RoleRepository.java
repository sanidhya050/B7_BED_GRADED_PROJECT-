package com.ems.repository;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("SELECT r from Role r where r.name= ?1")
	public Role findByName(String name);
}

