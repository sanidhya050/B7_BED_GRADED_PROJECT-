package com.ems.serviceImpl;

import java.util.HashSet;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.relation.Role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.RoleRepository;
import com.ems.repository.UserRepository;
import com.ems.service.EmployeeService;

@SuppressWarnings("rawtypes")

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	BCryptPasswordEncoder cryptPasswordEncoder;
	private EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

	@Override
	public java.util.List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();

		} else {
			throw new RuntimeException("Employee id - " + theId + " Not Found");
		}
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public Employee updateEmployee(Employee theEmployee) {
		Employee updatedEmployee = employeeRepository.findById(theEmployee.getId()).get();
		updatedEmployee.setFirstName(theEmployee.getFirstName());
		updatedEmployee.setLastName(theEmployee.getLastName());
		updatedEmployee.setEmail(theEmployee.getEmail());
		return this.employeeRepository.save(updatedEmployee);
	}

	@Override
	public Role saveRole(Role role) {

		Role theRole = roleRepository.findByName(role.getRoleName());
		if (theRole == null) {
			return roleRepository.save(role);
		} else {
			return theRole;
		}

	}

	@Override
	public User saveUser(User user) {
		user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
		// Handle Existing Roles
		Set<Role> theRoles = new HashSet<>();
		for (Role theRole : user.getRoles()) {
			Role tempRole = roleRepository.findByName(theRole.getRoleName());
			if (tempRole != null) {
				theRoles.add(tempRole);
			} else {
				theRoles.add(theRole);
			}
		}
		user.setRoles(theRoles);

		return userRepository.save(user);

	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public List<Employee> sortByFirstNameDesc() {
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}

	@Override
	public List<Role> viewRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<User> viewUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteRole(int roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(User theUser) {

		User existingUser = userRepository.findById(theUser.getId()).get();
		if (existingUser != null) {
			existingUser.setUsername(theUser.getUsername());
			existingUser.setPassword(cryptPasswordEncoder.encode(theUser.getPassword()));
			existingUser.setRoles(theUser.getRoles());
			userRepository.save(existingUser);
		}
		return existingUser;
	}

	@Override
	public Role updateRole(Role theRole) {
		Role existingRole = roleRepository.findById(theRole.getId()).get();
		if (existingRole != null) {
			existingRole.setRoleName(theRole.getRoleName());

			roleRepository.save(existingRole);
		}
		return existingRole;
	}

	@Override
	public User updateUser1(User theUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser111(User theUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser11(User theUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.ems.service.User updateUser(com.ems.service.User theUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.ems.service.User saveUser(com.ems.service.User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(user user) {
		// TODO Auto-generated method stub
		return null;
	}

}