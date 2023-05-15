package com.ems.controller;

import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class RestEmployeeController {
	private EmployeeService employeeService;

	public RestEmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	@GetMapping("/role/list")
	public List<Role> getAllRoles() {
		return employeeService.viewRoles();
	}

	@GetMapping("/user/list")
	public List<User> getAllUsers() {
		return employeeService.viewUsers();
	}

	// return list of employees
	@GetMapping("/employees/list")
	public List<Employee> findAll() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	// add mapping for GET /employees/{employeeId}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for PUT /employees - update existing employee

	/*
	 * @PutMapping("/employees/update") public Employee updateEmployee(@RequestBody
	 * Employee theEmployee) {
	 * 
	 * employeeService.save(theEmployee);
	 * 
	 * return theEmployee; }
	 */

	@PutMapping("/employees/update/{id}")
	public Employee updateEmployee(@RequestBody Employee theEmployee, @PathVariable("id") int employeeId) {
		return this.employeeService.updateEmployee(theEmployee);
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/employees/delete/{employeeId}")

	public String deleteEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		// throw exception if null

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String order) {
		if (order.equalsIgnoreCase("asc")) {
			return employeeService.sortByFirstNameAsc();
		} else if (order.equalsIgnoreCase("desc")) {
			return employeeService.sortByFirstNameDesc();
		}
		return null;
	}

	@DeleteMapping("/role/delete/{roleId}")
	public String deleteRole(@PathVariable int roleId) {
		employeeService.deleteRole(roleId);
		return "Deleted Role id - " + roleId;

	}

	@DeleteMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable int userId) {
		employeeService.deleteUser(userId);
		return "Deleted User id - " + userId;
	}

	@PutMapping("/user/update")
	public User updateUser(@RequestBody User theUser) {

		return employeeService.updateUser(theUser);

	}

	@PutMapping("/role/update")
	public Role updateRole(@RequestBody Role theRole) {

		return employeeService.updateRole(theRole);

	}

}