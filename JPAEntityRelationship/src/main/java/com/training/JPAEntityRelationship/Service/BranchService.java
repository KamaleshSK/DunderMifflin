package com.training.JPAEntityRelationship.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.JPAEntityRelationship.DTO.BranchDTO;
import com.training.JPAEntityRelationship.Models.Branch;
import com.training.JPAEntityRelationship.Models.Employees;
import com.training.JPAEntityRelationship.Repository.BranchRepository;
import com.training.JPAEntityRelationship.Repository.EmployeeRepository;

@Service
public class BranchService {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void saveBranchWithManager(String jsonBranchObject) {
		BranchDTO branchDTO = BranchDTO.newInstance(jsonBranchObject);
		
		Employees manager = employeeRepository.findByEmployeeId(branchDTO.getMgrId());
		
		Branch branch = Branch.builder()
				.branchName(branchDTO.getBranchName())
				.mgrId(manager)
				.mgrStartDate(branchDTO.getMgrStartDate())
				.build();
		
		branchRepository.save(branch);
	}
	
	public Branch findBranchByBranchId(String branchId) {
		
		if (branchId == null) return null;
		
		return branchRepository.findByBranchId(Long.valueOf(branchId));
	}
	
	public List<Branch> getAllBranches() {
		return null;
	}
	
	public void deleteBranchByBranchId(String branchId) {
		if (branchId == null) return;
		
		branchRepository.deleteById(Long.valueOf(branchId));
	}
	
}
