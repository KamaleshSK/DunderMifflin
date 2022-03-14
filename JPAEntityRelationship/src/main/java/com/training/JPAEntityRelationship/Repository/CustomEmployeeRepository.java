package com.training.JPAEntityRelationship.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

import com.training.JPAEntityRelationship.DTO.EmployeeDTO;

public interface CustomEmployeeRepository {
	
	@Modifying
	@Transactional
	void updateEmployeeDetailsByEmployeeId(Long employeeId, EmployeeDTO updatedEmployeeDTO);

	@Modifying
	@Transactional
	void updateEmployeeBranchByEmployeeId(Long employeeId, Long branchId);
	
	@Modifying
	@Transactional
	void assignClientToEmployeeByEmployeeId(Long employeeId, Long clientId);
}
