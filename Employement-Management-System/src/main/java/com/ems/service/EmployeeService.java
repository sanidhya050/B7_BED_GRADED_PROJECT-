package com.ems.service;

import java.util.List;


import javax.management.relation.Role;


import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.serviceImpl.user;

@Service
public interface EmployeeService {
	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);
	
	public Employee updateEmployee(Employee theEmployee);

	public void deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstNameAsc();

	public List<Employee> sortByFirstNameDesc();

	public User saveUser(User user);

	public Role saveRole(Role role);

	public List<Role> viewRoles();

	public List<User> viewUsers();

	public void deleteRole(int roleId);

	public void deleteUser(int userId);

	public User updateUser1(User theUser);

	public Role updateRole(Role theRole);

	public User updateUser111(User theUser);

	public User updateUser11(User theUser);

	public User updateUser(User theUser);

	com.ems.service.User updateUser(com.ems.service.User theUser);

	com.ems.service.User saveUser(com.ems.service.User user);

	User saveUser(user user);

	
}