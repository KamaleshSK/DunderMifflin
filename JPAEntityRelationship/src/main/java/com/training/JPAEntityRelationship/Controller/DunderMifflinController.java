package com.training.JPAEntityRelationship.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.JPAEntityRelationship.Models.Branch;
import com.training.JPAEntityRelationship.Models.Employees;
import com.training.JPAEntityRelationship.Service.BranchService;
import com.training.JPAEntityRelationship.Service.EmployeeService;

@RestController
@RequestMapping("/admin")
public class DunderMifflinController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	BranchService branchService;
	
	@PostMapping("create-employee")
	public void createEmployee(@RequestBody String employeePayload) {
		employeeService.saveEmployeeWithoutBranch(employeePayload);
	}
	
	@PostMapping("create-employee-with-branch")
	public void createEmployeeWithBranch(@RequestBody String employeePayload) {
		employeeService.saveEmployeeWithBranch(employeePayload);
	}
	
	@PostMapping("create-branch")
	public void createBranch(@RequestBody String branchPayload) {
		branchService.saveBranchWithManager(branchPayload);
	}
	
	@GetMapping("/get-all-employees")
	public List<Employees> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/get-all-employees-sorted-by-salary")
	public List<Employees> getAllEmployeesOrderedBySalary() {
		return employeeService.getAllEmployeesOrderedBySalary();
	}
	
	@GetMapping("/get-employees-by-gender")
	public List<Employees> getAllEmployeesByGender(@RequestParam String gender) {
		return employeeService.getEmployeesByGender(gender);
	}
	
	@PatchMapping("/update-employee-details-by-employee-id")
	public void updateEmployeeByEmployeeId(@RequestParam("employee_id") String employeeId, @RequestBody String employeePayload) {
		employeeService.updateEmployeeByEmployeeId(employeeId, employeePayload);
	}
	
	@PatchMapping("/assign-employee-to-branch")
	public void updateEmployeeBranch(@RequestParam("employee_id") String employeeId, @RequestParam("branch_id") String branchId) {
		employeeService.updateEmployeeBranchByEmployeeId(employeeId, branchId);
	}
	
	@GetMapping("/branch-details-by-branch-id")
	public Branch getBranchDetailsByBranchId(@RequestParam("branch_id") String branchId) {
		return branchService.findBranchByBranchId(branchId);
	}
	
	@GetMapping("/employee-details-in-pages")
	public List<Employees> getEmployeesByPages() {
		return employeeService.findAllPagination();
	}
	
	@GetMapping("/employee-details-sorted-by-salary")
	public List<Employees> getEmployeesSorting() {
		return employeeService.findAllSorting();
	}
}
