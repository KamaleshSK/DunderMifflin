package com.training.JPAEntityRelationship.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.training.JPAEntityRelationship.DTO.EmployeeDTO;
import com.training.JPAEntityRelationship.Models.Branch;
import com.training.JPAEntityRelationship.Models.Client;
import com.training.JPAEntityRelationship.Models.Employees;
import com.training.JPAEntityRelationship.Repository.BranchRepository;
import com.training.JPAEntityRelationship.Repository.ClientRepository;
import com.training.JPAEntityRelationship.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public void saveEmployeeWithoutBranch(String jsonEmployeeObject) {
		EmployeeDTO employeeDTO = EmployeeDTO.newInstance(jsonEmployeeObject);
		
		Employees employee = Employees.builder()
				.name(employeeDTO.getName())
				.birthday(employeeDTO.getBirthday())
				.gender(employeeDTO.getGender())
				.salary(employeeDTO.getSalary())
				.branchId(null)
				.superId(employeeDTO.getSuperId())
				.build();	
		
		employeeRepository.save(employee);
	}
	
	/*
	 * Add Branch Details as well to the save method to fix 
	 */
	public void saveEmployeeWithBranch(String jsonEmployeeObject) {
		EmployeeDTO employeeDTO = EmployeeDTO.newInstance(jsonEmployeeObject);
		
		Branch branch = branchRepository.findByBranchId(employeeDTO.getBranchId());
				
		Employees employee = Employees.builder()
				.name(employeeDTO.getName())
				.birthday(employeeDTO.getBirthday())
				.gender(employeeDTO.getGender())
				.salary(employeeDTO.getSalary())
				.branchId(branch)
				.superId(employeeDTO.getSuperId())
				.build();	
		
		employeeRepository.save(employee);
	}
	
	@Modifying
	@Transactional
	public void updateEmployeeBranchByEmployeeId(String employeeId, String branchId) {
		
		if (employeeId == null || branchId == null) return;
		
		employeeRepository.updateEmployeeBranchByEmployeeId(Long.valueOf(employeeId), Long.valueOf(branchId));
	}
	
	public List<Employees> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public List<Employees> getAllEmployeesOrderedBySalary() {
		return employeeRepository.getAllEmployeesOrderedBySalary();
	}
 	
	public List<Employees> getEmployeesByGender(String gender) {
		return employeeRepository.getEmployeesByGender(gender);
	}
	
	@Modifying
	@Transactional
	public void updateEmployeeByEmployeeId(String employeeId, String jsonEmployeeObject) {
		EmployeeDTO employeeDTO = EmployeeDTO.newInstance(jsonEmployeeObject);
		
		if (employeeId == null) return;
		
		employeeRepository.updateEmployeeDetailsByEmployeeId(Long.valueOf(employeeId), employeeDTO);
	}
	
	public List<Employees> findAllPagination() {
		Pageable firstPageWith2Records = PageRequest.of(0, 2);
		List<Employees> listOfEmployees = employeeRepository.findAll(firstPageWith2Records).getContent();
		
		return listOfEmployees;
	}
	
	public List<Employees> findAllSorting() {
		Pageable sortBySalary = PageRequest.of(0, 3, Sort.by("salary"));
		List<Employees> listOfEmployees = employeeRepository.findAll(sortBySalary).getContent();
		
		return listOfEmployees;
	}
	
	public void deleteEmployeeById(String employeeId) {
		if (employeeId == null) return;
		
		employeeRepository.deleteById(Long.valueOf(employeeId));
	}
	
	public void assignClientToEmployeeByEmployeeId(String clientId, String employeeId) {
		if (clientId == null || employeeId == null) return;
		
		employeeRepository.assignClientToEmployeeByEmployeeId(Long.valueOf(clientId), Long.valueOf(employeeId));
	}
} 
